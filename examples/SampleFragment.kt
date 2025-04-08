package com.effectssdk.demo.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.AudioTrack
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.effectssdk.audio.AudioEffectsSDK
import com.effectssdk.audio.AudioSdkFactory
import com.effectssdk.audio.LicenseStatus
import com.effectssdk.audio.pipeline.AudioPipeline
import com.effectssdk.audio.pipeline.InputType
import com.effectssdk.audio.pipeline.LatencyMode
import com.effectssdk.audio.pipeline.PipelineConfig
import com.effectssdk.demo.SpeechRecorder
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

class SampleFragment : BaseFragment() {
	private lateinit var sdkFactory: AudioSdkFactory
	private lateinit var audioPipeline: AudioPipeline

	private lateinit var recorder: AudioRecord
	private lateinit var track: AudioTrack

	private val recordingInProgress: AtomicBoolean = AtomicBoolean(false)
	private val inputAudioBuffer = ByteArray(480 * 10)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		createSdkInstance()
		createAudioRecord()
		createAudioTrack()
		startRecording()
	}


	private fun createAudioTrack() {
		val audioFormat = AudioFormat.Builder()
			.setEncoding(AudioFormat.ENCODING_PCM_16BIT)
			.setSampleRate(48000)
			.setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
			.build()
		track = AudioTrack.Builder()
			.setAudioFormat(audioFormat)
			.setBufferSizeInBytes(480 * 10)
			.build()
	}

	fun play(data: ByteArray) {
		try {
			if (track.state != AudioTrack.PLAYSTATE_PLAYING) {
				track.play()
			}
		} catch (e: IllegalStateException) {
			Log.w(this.javaClass.simpleName, "Illegal player state")
		}

		Executors.newSingleThreadExecutor().submit {
			try {
				track.write(data, 0, data.size, AudioTrack.WRITE_BLOCKING)
			} catch (e: IllegalStateException) {
				Log.w(this.javaClass.simpleName, "Illegal player state")
			}
		}
	}

	fun createSdkInstance() {
		sdkFactory = AudioEffectsSDK.getAudioSdkFactory()
		sdkFactory.auth("PLACE_YOUR_CUSTOMER_ID_HERE") { licenseStatus ->
			when (licenseStatus) {
				LicenseStatus.ACTIVE -> {
					val pipelineConfig = PipelineConfig(
						sampleRate = 48000,
						numberOfChannels = 1,
						dataType = InputType.ENCODING_PCM_16BIT,
						latencyMode = LatencyMode.PLAYBACK
					)
					audioPipeline = sdkFactory.createAudioPipeline(pipelineConfig)
					audioPipeline.enableNoiseSuppression(true)
				}

				LicenseStatus.INACTIVE -> {}
				LicenseStatus.EXPIRED -> {}
				LicenseStatus.ERROR -> {}
			}

		}
	}

	fun createAudioRecord() {
		val permissionStatus = ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
		if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
			recorder = AudioRecord.Builder()
				.setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION)
				.setAudioFormat(
					AudioFormat.Builder()
						.setEncoding(AudioFormat.ENCODING_PCM_16BIT)
						.setSampleRate(48000)
						.setChannelMask(AudioFormat.CHANNEL_IN_MONO)
						.build()
				)
				.setBufferSizeInBytes(
					AudioRecord.getMinBufferSize(48000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT) * 10
				)
				.build()

		} else {
			Log.w(SpeechRecorder::class.java.simpleName, "Microphone permission is not granted")
		}
	}

	fun startRecording() {
		recordingInProgress.set(true)
		recorder?.startRecording()
		Executors.newSingleThreadExecutor().execute {
			try {
				while (recordingInProgress.get()) {
					val result: Int = recorder.read(inputAudioBuffer, 0, 480 * 10, AudioRecord.READ_NON_BLOCKING)
					if (result > 0) {
						val result = audioPipeline.process(inputAudioBuffer.copyOfRange(0, result))
						play(result)
					}
				}
			} catch (e: IOException) {
				e.printStackTrace()
				throw RuntimeException("Writing of recorded audio failed", e)
			}

		}
	}
}