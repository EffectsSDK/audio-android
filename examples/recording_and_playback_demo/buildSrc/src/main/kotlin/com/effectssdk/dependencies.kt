package com.effectssdk

object Versions {

	//Demo app version
	private const val appMajorVersion = 1
	private const val appMinorVersion = 2
	private const val appPatchVersion = 0

	const val compileSdkVersion = 33
	const val targetSdkVersion = 33
	const val minSdkVersion = 27

	fun getAppVersionCode(): Int {
		return appMajorVersion * 10000 + appMinorVersion * 100 + appPatchVersion
	}

	fun getAppVersionName(): String {
		return "$appMajorVersion.$appMinorVersion.$appPatchVersion"
	}


}

object Libs {

	object AndroidX {
		const val core = "androidx.core:core-ktx:1.7.0"
		const val appCompat = "androidx.appcompat:appcompat:1.6.1"
		const val material = "com.google.android.material:material:1.9.0"
		const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:2.6.0"
		const val navigationUi = "androidx.navigation:navigation-fragment-ktx:2.6.0"
		const val constraintLayout = "androidx.navigation:navigation-ui-ktx:2.1.4"
		const val materialIcons = "androidx.compose.material:material-icons-extended-android:1.5.0"
		const val androidMaterialIcons = "androidx.compose.material:material-icons-extended-android:1.5.0"
	}

	const val rxJava = "io.reactivex.rxjava3:rxjava:3.1.6"
	const val rxAndroid = "io.reactivex.rxjava3:rxandroid:3.0.2"
}
