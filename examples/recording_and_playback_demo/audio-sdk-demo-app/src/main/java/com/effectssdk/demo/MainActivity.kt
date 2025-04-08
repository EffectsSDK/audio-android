package com.effectssdk.demo

import android.Manifest.permission.*
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
		val navController = navHostFragment.navController
	}

	override fun onStart() {
		super.onStart()
		checkAndRequestPermissions()
	}

	private fun checkAndRequestPermissions() {
		val isAudioRecordPermissionGranted = ContextCompat.checkSelfPermission(this, RECORD_AUDIO)
		val isReadExternalStoragePermissionGranted = ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE)
		val isWriteExternalStoragePermissionGranted = ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE)
		val permissionList = ArrayList<String>()
		if (isAudioRecordPermissionGranted != PackageManager.PERMISSION_GRANTED) permissionList.add(RECORD_AUDIO)
		if (isReadExternalStoragePermissionGranted != PackageManager.PERMISSION_GRANTED) permissionList.add(READ_EXTERNAL_STORAGE)
		if (isWriteExternalStoragePermissionGranted != PackageManager.PERMISSION_GRANTED) permissionList.add(WRITE_EXTERNAL_STORAGE)
		if (permissionList.isNotEmpty()) {
			ActivityCompat.requestPermissions(
				this,
				permissionList.toTypedArray(),
				100
			);
		}
	}
}