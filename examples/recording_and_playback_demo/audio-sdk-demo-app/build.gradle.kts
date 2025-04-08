import com.effectssdk.Libs
import com.effectssdk.Versions

plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
}

android {
	namespace = "com.effectssdk.demo"
	compileSdk = Versions.compileSdkVersion

	defaultConfig {
		applicationId = "com.effectssdk.demo"
		minSdk = Versions.minSdkVersion
		targetSdk = Versions.targetSdkVersion
		versionCode = Versions.getAppVersionCode()
		versionName = Versions.getAppVersionName()

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}


	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	kotlinOptions {
		jvmTarget = "1.8"
	}

}

dependencies {
	implementation(project(":domain"))
	implementation(files("libs/Audio-effects-sdk-v.1.5.0.112082.aar"))
	implementation(Libs.rxJava)
	implementation(Libs.rxAndroid)
	implementation(Libs.AndroidX.core)
	implementation(Libs.AndroidX.appCompat)
	implementation(Libs.AndroidX.material)
	implementation(Libs.AndroidX.materialIcons)
	implementation(Libs.AndroidX.androidMaterialIcons)
	implementation(Libs.AndroidX.constraintLayout)
	implementation(Libs.AndroidX.navigationFragment)
	implementation(Libs.AndroidX.navigationUi)
}