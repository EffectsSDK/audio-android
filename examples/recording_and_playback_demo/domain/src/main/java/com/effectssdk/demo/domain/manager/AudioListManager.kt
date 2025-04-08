package com.effectssdk.demo.domain.manager

import com.effectssdk.demo.domain.model.AudioModel
import com.effectssdk.demo.domain.model.PlaybackOptionModel
import com.effectssdk.demo.domain.model.SdkStatus
import io.reactivex.rxjava3.core.Observable

interface AudioListManager {
	val audioList: Observable<List<AudioModel>>
	val sdkLicenseStatus: Observable<SdkStatus>
	val playbackOptions: Observable<PlaybackOptionModel>

	fun updateAudioList(audioData: List<AudioModel>)
	fun updateAudioModel(audioModel: AudioModel)
	fun isAudioSelected(audioFile: String): Boolean
	fun updateLicenseStatus(status: SdkStatus)
	fun enablePlayback(enable: Boolean)
	fun enableNoiseSuppressionForPlayback(enable: Boolean)
}