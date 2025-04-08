package com.effectssdk.demo.presenter

import android.content.Context
import android.widget.Toast
import com.effectssdk.audio.AudioEffectsSDK
import com.effectssdk.audio.AudioSdkFactory
import com.effectssdk.audio.LicenseStatus
import com.effectssdk.audio.pipeline.AudioPipeline
import com.effectssdk.audio.pipeline.InputType
import com.effectssdk.audio.pipeline.LatencyMode
import com.effectssdk.audio.pipeline.PipelineConfig
import com.effectssdk.demo.AudioPlayer
import com.effectssdk.demo.BuildConfig
import com.effectssdk.demo.RecorderCallback
import com.effectssdk.demo.SpeechRecorder
import com.effectssdk.demo.TypeConverter
import com.effectssdk.demo.controller.AudioFileController
import com.effectssdk.demo.domain.manager.AudioListManager
import com.effectssdk.demo.domain.model.AudioModel
import com.effectssdk.demo.domain.model.SdkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.Date
import java.util.concurrent.Executors
import java.util.concurrent.Future


class AudioDemoFragmentPresenter(
	private val context: Context,
	private val audioListManager: AudioListManager,
	private val view: AudioDemoFragmentView
) : BasePresenter() {

	private val audioFileController: AudioFileController = AudioFileController(context)
	private var disposable = CompositeDisposable()
	private val speechRecorder: SpeechRecorder = SpeechRecorder(context, audioFileController)

	private var player: AudioPlayer? = null
	private val audioPlayerExecutor = Executors.newSingleThreadExecutor()

	private var audioSdkFactory: AudioSdkFactory? = null
	private val blockLen = 2048
	private var denoiseReadTask: Future<*>? = null

	private var audioPipeline: AudioPipeline? = null
	private var isPlaybackEnabled = false
	private var isNoiseSuppressionForPlaybackEnabled = false
	private var selectedAudio: AudioModel? = null
	private var playedAudio: AudioModel? = null


	private val defaultPipelineConfig = PipelineConfig(
		sampleRate = 48000,
		numberOfChannels = 1,
		dataType = InputType.ENCODING_PCM_FLOAT,
		latencyMode = LatencyMode.STREAMING
	)

	override fun subscribe() {
		audioListManager.updateAudioList(getAudioFileList())
		createSdkPipeline()
		disposable.addAll(
			audioListManager.audioList
				.doOnNext {
					val selected = it.firstOrNull { item -> item.isSelected }
					selected?.let {
						selectedAudio = it
					}
				}
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe {
					view.updateAudioList(it)
				},
			audioListManager.sdkLicenseStatus
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe {
					view.showProgressBar(false)
				},
			audioListManager.playbackOptions
				.subscribe {
					isPlaybackEnabled = it.isPlaybackEnabled
					isNoiseSuppressionForPlaybackEnabled = it.isNoiseSuppressionEnabled
				}
		)

		player = AudioPlayer(defaultPipelineConfig.sampleRate)
	}

	override fun unsubscribe() {
		denoiseReadTask?.cancel(true)
		denoiseReadTask = null
		disposable.dispose()
		disposable = CompositeDisposable()
		player?.stop()
		playedAudio = null
		speechRecorder.release()
		audioPipeline?.release()
		audioSdkFactory?.release()
		audioPipeline = null
		audioSdkFactory = null
	}

	fun onRecordAudioButtonClicked() {
		player?.stop()
		selectedAudio?.let {
			audioListManager.updateAudioModel(it.copy(isPlayedOriginal = false, isPlayedDenoise = false))
		}

		if (!speechRecorder.isRecordingInProgress()) {
			audioPipeline?.setLatencyMode(LatencyMode.PLAYBACK)
			speechRecorder.startRecording()
			speechRecorder.setCallback(object : RecorderCallback {
				var numOfFloatSamples = 0
				var sec = 0
				override fun onData(data: FloatArray) {
					numOfFloatSamples += data.size
					sec = numOfFloatSamples / defaultPipelineConfig.sampleRate
					view.updateRecordButtonState(speechRecorder.isRecordingInProgress(), sec)
					if (isPlaybackEnabled) {
						if (isNoiseSuppressionForPlaybackEnabled) {
							val result = audioPipeline?.process(data)
							player?.play(result!!)
						} else {
							player?.play(data)
						}
					}

				}

				override fun onData(data: ByteArray) {
					numOfFloatSamples += data.size
					sec = numOfFloatSamples / defaultPipelineConfig.sampleRate
					view.updateRecordButtonState(speechRecorder.isRecordingInProgress(), sec)
					if (isPlaybackEnabled) {
						if (isNoiseSuppressionForPlaybackEnabled) {
							val result = audioPipeline?.process(data)
							player?.play(result!!)
						} else {
							player?.play(data)
						}
					}
				}

				override fun onRecordStop() {
					audioListManager.updateAudioList(getAudioFileList())
					view.updateRecordButtonState(speechRecorder.isRecordingInProgress(), sec)
					speechRecorder.setCallback(null)

				}
			})
		} else {
			speechRecorder.stopRecording()
			stopAudio()
		}
	}

	fun removeAudio(audioFile: String) {
		audioFileController.removeFile(audioFile)
		audioListManager.updateAudioList(getAudioFileList())
	}

	private fun createSdkPipeline() {
		stopAudio()
		if (audioSdkFactory == null) audioSdkFactory = AudioEffectsSDK.getAudioSdkFactory()
		audioSdkFactory?.auth("place_your_customer_id_here") { licenseStatus ->
			when (licenseStatus) {
				LicenseStatus.ACTIVE -> {
					audioPipeline = audioSdkFactory?.createAudioPipeline(defaultPipelineConfig)
					audioPipeline?.enableNoiseSuppression(true)
				}

				LicenseStatus.INACTIVE -> view.showToast("License is inactive")
				LicenseStatus.EXPIRED -> view.showToast("License is expired")
				LicenseStatus.ERROR -> view.showToast("Internal error")
			}
			val status = if (licenseStatus == LicenseStatus.ACTIVE) SdkStatus.AVAILABLE else SdkStatus.UNAVAILABLE
			audioListManager.updateLicenseStatus(status)
		}

	}

	fun stopAudio() {
		denoiseReadTask?.cancel(true)
		denoiseReadTask = null
		player?.stop()
		playedAudio = null
	}

	fun renameAudioFile(audioFile: String, newFileName: String) {
		audioFileController.renameFile(audioFile, newFileName)
//		if (audioListManager.isAudioSelected(audioFile)) audioListManager.selectModel(AudioModel())
		audioListManager.updateAudioList(getAudioFileList())

	}

	fun onAudioItemClicked(audioModel: AudioModel) {
		audioListManager.updateAudioModel(audioModel)
	}

	private fun getAudioFileList(): List<AudioModel> {
		return audioFileController.getAllAvailableFiles()
			.map {
				val date = Date(it.lastModified())
				AudioModel(
					it.name,
					isPlayedDenoise = false,
					isPlayedOriginal = false,
					creationDate = date
				)
			}
	}

	fun playAudio(audioModel: AudioModel) {
		playedAudio?.let {
			val isOrigin = it.isPlayedOriginal
			it.isPlayedDenoise = false
			it.isPlayedOriginal = false
			it.isSelected = selectedAudio?.name == it.name
			audioListManager.updateAudioModel(it)
			stopAudio()
			if ((it.name == audioModel.name) and isOrigin) {
				return
			}
		}
		audioModel.isPlayedDenoise = false
		audioModel.isPlayedOriginal = true
		audioListManager.updateAudioModel(audioModel)
		playedAudio = audioModel
		if (audioModel.name.isNotEmpty()) {
			denoiseReadTask = audioPlayerExecutor.submit {
				val audioBytes = audioFileController.readAudioPCMFile(audioModel.name)
				player?.setOnStopListener {
					player?.setOnStopListener(null)
					audioModel.isPlayedDenoise = false
					audioModel.isPlayedOriginal = false
					audioModel.isSelected = selectedAudio?.name == audioModel.name
					audioListManager.updateAudioModel(audioModel)
					stopAudio()
				}
				val floatData = TypeConverter.byteToFloat(audioBytes)
				player?.audioSize = floatData.size
				player?.play(floatData)
			}
		}

	}

	fun playDenoiseAudio(audioModel: AudioModel) {
		playedAudio?.let {
			val isDenoise = it.isPlayedDenoise
			it.isPlayedDenoise = false
			it.isPlayedOriginal = false
			it.isSelected = selectedAudio?.name == it.name
			audioListManager.updateAudioModel(it)
			stopAudio()
			if ((it.name == audioModel.name) and isDenoise) {
				return
			}
		}
		audioModel.isPlayedDenoise = true
		audioModel.isPlayedOriginal = false
		audioListManager.updateAudioModel(audioModel)
		playedAudio = audioModel
		audioPipeline?.setLatencyMode(LatencyMode.FILE)
		if (audioModel.name.isNotEmpty()) {
			val audioBytes = audioFileController.readAudioPCMFile(audioModel.name)
			val audioFloats = TypeConverter.byteToFloat(audioBytes)
			val numBlocks = (audioFloats.size - (blockLen)).floorDiv(blockLen)
			denoiseReadTask = audioPlayerExecutor.submit {
				var sdkInputBlock = FloatArray(blockLen)
				var realSize = 0
				player?.setOnStopListener {
					player?.setOnStopListener(null)
					audioModel.isPlayedDenoise = false
					audioModel.isPlayedOriginal = false
					audioModel.isSelected = selectedAudio?.name == audioModel.name
					audioListManager.updateAudioModel(audioModel)
					stopAudio()
				}
				player?.audioSize = audioFloats.size


				for (i in 0..numBlocks) {
					audioFloats.copyInto(sdkInputBlock, 0, i * blockLen, (i * blockLen) + blockLen)
					audioPipeline?.let {
						val sdkOutputBlock = it.process(sdkInputBlock)
						realSize += sdkInputBlock.size
						player?.play(sdkOutputBlock)
					}
				}

				sdkInputBlock = FloatArray(blockLen)
				audioFloats.copyInto(sdkInputBlock, 0, (numBlocks + 1) * blockLen, audioFloats.size - 1)
				audioPipeline?.let {
					val sdkOutputBlock = it.process(sdkInputBlock)
					player?.play(sdkOutputBlock)
					val flushed = it.flushFloats()
					player?.play(flushed)
				}
			}
		}
	}

	fun enablePlayback(enable: Boolean) {
		audioListManager.enablePlayback(enable)
	}

	fun enableNoiseSuppressionForPlayback(enable: Boolean) {
		audioListManager.enableNoiseSuppressionForPlayback(enable)
	}

	interface AudioDemoFragmentView {
		fun updateAudioList(list: List<AudioModel>)
		fun showProgressBar(isShown: Boolean)
		fun updateRecordButtonState(isRecordEnabled: Boolean, seconds: Int)
		fun showToast(text: String)
	}

}