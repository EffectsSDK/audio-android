import com.effectssdk.Libs
import com.effectssdk.Versions

plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
}

android {
	namespace = "com.effectssdk.demo.domain"
	compileSdk = Versions.compileSdkVersion

	defaultConfig {
		minSdk = Versions.minSdkVersion
		targetSdk = Versions.targetSdkVersion

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
	implementation(Libs.rxJava)
	implementation(Libs.rxAndroid)
	implementation(Libs.AndroidX.core)
	implementation(Libs.AndroidX.appCompat)
	implementation(Libs.AndroidX.material)

}