package com.effectssdk.demo.domain.manager

import com.effectssdk.demo.domain.model.AudioModel
import com.effectssdk.demo.domain.model.PlaybackOptionModel
import com.effectssdk.demo.domain.model.SdkStatus
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class AudioListManagerImpl : AudioListManager {

	private val availableAudioFileList = ArrayList<AudioModel>()
	private val audioListSubject = BehaviorSubject.createDefault(emptyList<AudioModel>())
	private val selectedAudioSubject = BehaviorSubject.createDefault(AudioModel())
	private val sdkLicenseStatusSubject = BehaviorSubject.createDefault(SdkStatus.UNAVAILABLE)
	private val playbackStatusSubject = BehaviorSubject.createDefault(PlaybackOptionModel())

	override val audioList: Observable<List<AudioModel>>
		get() = audioListSubject.hide()


	override val sdkLicenseStatus: Observable<SdkStatus>
		get() = sdkLicenseStatusSubject.hide()

	override val playbackOptions: Observable<PlaybackOptionModel>
		get() = playbackStatusSubject.hide()

	override fun isAudioSelected(audioFile: String): Boolean {
		return selectedAudioSubject.value?.name == audioFile
	}

	override fun updateLicenseStatus(status: SdkStatus) {
		sdkLicenseStatusSubject.onNext(status)
	}

	override fun enablePlayback(enable: Boolean) {
		val previous = playbackStatusSubject.value!!
		playbackStatusSubject.onNext(previous.copy(isPlaybackEnabled = enable))
	}

	override fun enableNoiseSuppressionForPlayback(enable: Boolean) {
		val previous = playbackStatusSubject.value!!
		playbackStatusSubject.onNext(previous.copy(isNoiseSuppressionEnabled = enable))
	}

	override fun updateAudioList(audioData: List<AudioModel>) {
		availableAudioFileList.removeIf { !audioData.contains(it) }
		audioData.forEach {
			if (!availableAudioFileList.contains(it)) {
				availableAudioFileList.add(it)
			}
		}
		onNextAudioList()
	}

	override fun updateAudioModel(audioModel: AudioModel) {
		val available = availableAudioFileList.firstOrNull() { it.name == audioModel.name }
		available?.let {
			if (it.isSelected) {
				availableAudioFileList.forEach { item -> if (item.name != audioModel.name) item.isSelected = false }
			}
			available.isPlayedOriginal = audioModel.isPlayedOriginal
			available.isPlayedDenoise = audioModel.isPlayedDenoise
			available.isSelected = audioModel.isSelected
		}
		onNextAudioList()
	}

	private fun onNextAudioList() {
		val newList = ArrayList<AudioModel>()
		newList.addAll(availableAudioFileList)
		audioListSubject.onNext(newList)
	}
}