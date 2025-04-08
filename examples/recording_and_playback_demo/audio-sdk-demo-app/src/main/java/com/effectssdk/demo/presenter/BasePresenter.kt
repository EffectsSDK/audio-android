package com.effectssdk.demo.presenter

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

abstract class BasePresenter : DefaultLifecycleObserver {


	override fun onStart(owner: LifecycleOwner) {
		super.onStart(owner)
		subscribe()
	}

	override fun onStop(owner: LifecycleOwner) {
		unsubscribe()
		super.onStop(owner)
	}

	protected open fun subscribe() {}

	protected open fun unsubscribe() {}
}