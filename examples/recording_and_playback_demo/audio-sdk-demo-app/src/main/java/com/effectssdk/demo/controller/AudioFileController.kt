package com.effectssdk.demo.controller

import android.content.Context
import android.content.ContextWrapper
import android.os.Environment
import com.effectssdk.demo.TypeConverter
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.util.Calendar
import java.util.LinkedList

class AudioFileController(private val context: Context) {

	private val contextWrapper = ContextWrapper(context)
	private val rootDir: File
		get() = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC) ?: throw NullPointerException(DIR_ACCESS_ERROR_MSG)


	fun getAllAvailableFiles(): ArrayList<File> {
		val list = ArrayList<File>()
		val fileArray = rootDir.listFiles()
		fileArray?.forEach {
			list.add(it)
		}
		return list
	}

	fun readAudioPCMFile(filename: String): ByteArray {
		val file = File(rootDir, filename)
		val fileInput: FileInputStream?
		try {
			fileInput = FileInputStream(file)
		} catch (e: FileNotFoundException) {
			e.printStackTrace()
			return ByteArray(0)
		}
		val fileBytes = fileInput.readBytes()
		fileInput.close()
		return fileBytes
	}

	fun removeFile(audioFile: String) {
		val file = File(rootDir, audioFile)
		if (file.exists()) {
			file.delete()
		}
	}

	fun renameFile(filename: String, newFileName: String) {
		val file = File(rootDir, filename)
		if (file.exists()) {
			file.renameTo(File(rootDir, newFileName))
		}
	}

	fun writeAudioPCMFile(fileChunkList: LinkedList<FloatArray>) {
		val file = File(getFilePath())
		FileOutputStream(file).use { outStream ->
			fileChunkList.forEach {
				outStream.write(TypeConverter.floatToByte(it))
			}
		}
	}

	private fun getFilePath(): String {
		val calendar = Calendar.getInstance()
		val fileName = FILENAME_HEADER +
				"${calendar.get(Calendar.HOUR_OF_DAY)}:" +
				"${calendar.get(Calendar.MINUTE)}:" +
				"${calendar.get(Calendar.SECOND)}"
		val file = File(rootDir, fileName)
		return file.absolutePath
	}

	private companion object {
		const val FILENAME_HEADER = "Test record "
		const val DIR_ACCESS_ERROR_MSG = "This dir is unavailable now"
	}

}