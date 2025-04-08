package com.effectssdk.demo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.effectssdk.demo.AudioSdkDemoApplication

abstract class BaseFragment : Fragment() {

	lateinit var application: AudioSdkDemoApplication

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		application = requireActivity().application as AudioSdkDemoApplication
	}

}