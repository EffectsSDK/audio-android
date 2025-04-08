package com.effectssdk.demo

import android.media.AudioFormat
import android.media.AudioTrack
import android.media.AudioTrack.OnPlaybackPositionUpdateListener
import android.util.Log
import java.util.concurrent.Executors
import java.util.concurrent.Future

class AudioPlayer(
	private val sampleRate: Int,
	private val playerBufferSize: Int = 8192
) {
	var audioSize = 0

	private var stopListener: Listener? = null
	private val audioPlayerExecutor = Executors.newSingleThreadExecutor()
	private val audioFormat = AudioFormat.Builder()
		.setEncoding(AudioFormat.ENCODING_PCM_FLOAT)
		.setSampleRate(sampleRate)
		.setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
		.build()
	private var player: AudioTrack? = null
	private var writeFuture: Future<*>? = null

	fun play(data: FloatArray) {
		if (player == null) {
			createAudioTrack()
		}
		try {
			if (player?.state != AudioTrack.PLAYSTATE_PLAYING) {
				player?.play()
			}
		} catch (e: IllegalStateException) {
			Log.w(this.javaClass.simpleName, "Illegal player state")
		}

		writeFuture = audioPlayerExecutor.submit {
			try {
				player?.write(data, 0, data.size, AudioTrack.WRITE_BLOCKING)
			} catch (e: IllegalStateException) {
				Log.w(this.javaClass.simpleName, "Illegal player state")
			}
		}
	}

	fun play(data: ByteArray) {
		if (player == null) {
			createAudioTrack()
		}
		try {
			if (player?.state != AudioTrack.PLAYSTATE_PLAYING) {
				player?.play()
			}
		} catch (e: IllegalStateException) {
			Log.w(this.javaClass.simpleName, "Illegal player state")
		}

		writeFuture = audioPlayerExecutor.submit {
			try {
				player?.write(data, 0, data.size, AudioTrack.WRITE_BLOCKING)
			} catch (e: IllegalStateException) {
				Log.w(this.javaClass.simpleName, "Illegal player state")
			}
		}
	}

	fun stop() {
		writeFuture?.cancel(true)
		writeFuture = null
		player?.pause()
		player?.flush()
		player?.stop()
		player?.release()
		player = null
		audioSize = 0
	}

	private fun createAudioTrack() {
		player = AudioTrack.Builder()
			.setAudioFormat(audioFormat)
			.setBufferSizeInBytes(playerBufferSize)
			.build()
		if (audioSize != 0) {
			player?.notificationMarkerPosition = audioSize - 1
			player?.setPlaybackPositionUpdateListener(object : OnPlaybackPositionUpdateListener {
				override fun onMarkerReached(track: AudioTrack?) {
					stop()
					stopListener?.onStop()
				}

				override fun onPeriodicNotification(track: AudioTrack?) {

				}

			})
		}
	}

	fun setOnStopListener(listener: Listener?) {
		stopListener = listener
	}

	fun interface Listener {
		fun onStop()
	}

}