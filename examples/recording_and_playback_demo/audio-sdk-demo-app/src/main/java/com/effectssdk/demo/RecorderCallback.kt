package com.effectssdk.demo

interface RecorderCallback {

	fun onData(data: FloatArray)
	fun onData(data: ByteArray)
	fun onRecordStop()
}