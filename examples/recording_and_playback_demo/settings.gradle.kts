pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
}
dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()
	}
}
rootProject.name = "Audio Effects SDK demo"
include(":audio-sdk-demo-app")
include(":domain")
