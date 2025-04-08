package com.effectssdk.demo

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.effectssdk.demo.domain.manager.AudioListManager
import com.effectssdk.demo.domain.manager.AudioListManagerImpl
import com.effectssdk.demo.domain.manager.AudioListManagerWrapper

class AudioSdkDemoApplication : Application() {

	val audioListManager: AudioListManager = AudioListManagerWrapper(AudioListManagerImpl())

	override fun onCreate() {
		super.onCreate()
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
	}

}