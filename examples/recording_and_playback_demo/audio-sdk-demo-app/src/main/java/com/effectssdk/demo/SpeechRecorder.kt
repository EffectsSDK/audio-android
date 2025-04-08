package com.effectssdk.demo

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import androidx.core.app.ActivityCompat
import com.effectssdk.demo.controller.AudioFileController
import java.io.IOException
import java.util.LinkedList
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

class SpeechRecorder(
	private val context: Context,
	private val audioFileController: AudioFileController,
	private var callback: RecorderCallback? = null
) {
	private val audioReaderExecutor = Executors.newSingleThreadExecutor()
	private val recordingInProgress: AtomicBoolean = AtomicBoolean(false)
	private var recorder: AudioRecord? = null
	private val chunksList = LinkedList<FloatArray>()
	private val tmpAudioBuffer = FloatArray(15360)


	init {
		initRecorder()
	}

	fun setCallback(recorderCallback: RecorderCallback?) {
		callback = recorderCallback
	}

	private fun initRecorder() {
		val permissionStatus = ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
		if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
			recorder = AudioRecord.Builder()
				.setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION)
				.setAudioFormat(
					AudioFormat.Builder()
						.setEncoding(AUDIO_FORMAT)
						.setSampleRate(SAMPLING_RATE_IN_HZ)
						.setChannelMask(CHANNEL_CONFIG)
						.build()
				)
				.setBufferSizeInBytes(BUFFER_SIZE)
				.build()

		} else {
			Log.w(SpeechRecorder::class.java.simpleName, "Microphone permission is not granted")
		}
	}

	fun startRecording() {
		if (recorder == null) {
			initRecorder()
		}
		recordingInProgress.set(true)
		recorder?.startRecording()
		audioReaderExecutor.execute {
			try {
				while (recordingInProgress.get()) {
					val result: Int = recorder?.read(tmpAudioBuffer, 0, 15360, AudioRecord.READ_NON_BLOCKING)!!
					if (result > 0) {
						val arr = tmpAudioBuffer.copyOfRange(0, result)
						chunksList.add(arr)
						callback?.onData(arr)
					}
				}
			} catch (e: IOException) {
				e.printStackTrace()
				throw RuntimeException("Writing of recorded audio failed", e)
			}
			audioFileController.writeAudioPCMFile(chunksList)
			chunksList.clear()
			callback?.onRecordStop()
		}
	}

	fun stopRecording() {
		recorder?.let {
			recordingInProgress.set(false)
			it.stop()
		}
	}

	fun release() {
		stopRecording()
		recorder?.release()
		recorder = null

	}

	fun isRecordingInProgress(): Boolean = recordingInProgress.get()

	companion object {
		private const val SAMPLING_RATE_IN_HZ = 48000
		private const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
		private const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_FLOAT
		private const val BUFFER_SIZE_FACTOR = 10
		private val BUFFER_SIZE: Int = AudioRecord.getMinBufferSize(
			SAMPLING_RATE_IN_HZ,
			CHANNEL_CONFIG,
			AUDIO_FORMAT
		) * BUFFER_SIZE_FACTOR
	}
}