package com.effectssdk.demo

import java.nio.ByteBuffer
import java.nio.ByteOrder

internal object TypeConverter {

	fun floatToByte(data: FloatArray): ByteArray {
		val outputArray = ByteArray(data.size * 2)
		var offset = 0
		for (float in data) {
			floatToPcm16(float).copyInto(outputArray, offset)
			offset += 2
		}
		return outputArray
	}

	fun byteToFloat(data: ByteArray): FloatArray {
		val buf = ByteBuffer.wrap(data)
			.order(ByteOrder.LITTLE_ENDIAN)
			.asShortBuffer()
		val shortArray = ShortArray(buf.capacity())
		buf.get(shortArray)
		val soundFloats = FloatArray(shortArray.size)
		for (i in shortArray.indices) {
			soundFloats[i] = shortArray[i].toFloat() / 0x8000
		}
		return soundFloats
	}

	private val convertBuffer = ByteBuffer.allocate(2).apply {
		order(ByteOrder.LITTLE_ENDIAN)
	}

	private fun floatToPcm16(value: Float): ByteArray {
		convertBuffer.clear()
		convertBuffer.putShort((value * 32768F).toInt().toShort())
		return convertBuffer.array()
	}

}