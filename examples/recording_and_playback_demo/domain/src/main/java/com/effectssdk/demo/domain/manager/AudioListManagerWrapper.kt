package com.effectssdk.demo.domain.manager

import com.effectssdk.demo.domain.model.AudioModel
import com.effectssdk.demo.domain.model.PlaybackOptionModel
import com.effectssdk.demo.domain.model.SdkStatus
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.Executors

class AudioListManagerWrapper(private val manager: AudioListManagerImpl) : AudioListManager {

	private val executor = Executors.newSingleThreadExecutor()

	override val audioList: Observable<List<AudioModel>>
		get() = manager.audioList

	override val sdkLicenseStatus: Observable<SdkStatus>
		get() = manager.sdkLicenseStatus
	override val playbackOptions: Observable<PlaybackOptionModel>
		get() = manager.playbackOptions

	override fun updateAudioList(audioData: List<AudioModel>) {
		executor.execute { manager.updateAudioList(audioData) }
	}

	override fun updateAudioModel(audioModel: AudioModel) {
		manager.updateAudioModel(audioModel)
	}

	override fun updateLicenseStatus(status: SdkStatus) {
		executor.execute { manager.updateLicenseStatus(status) }
	}

	override fun enablePlayback(enable: Boolean) {
		manager.enablePlayback(enable)
	}

	override fun enableNoiseSuppressionForPlayback(enable: Boolean) {
		manager.enableNoiseSuppressionForPlayback(enable)
	}

	override fun isAudioSelected(audioFile: String): Boolean {
		return manager.isAudioSelected(audioFile)
	}


}