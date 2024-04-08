![Effects SDK logo](assets/Logo.png "a title")

# Android Audio effects SDK

Add real-time AI audio denoise and echo cancellation to your product in a hour.

## Requirements

1. Use Android 9.0 as min API version
2. Obtaining Effects SDK Customer ID

## Documentation

1. ![API reference](assets/api-reference.md)
2. Look code example below

## Demo

For the best **quality** - use **Quality** preset. It provides the top quality of denoise functionality but requires good hardware (latest
CPU with high capabilities).

For the best **performance** – use **Speed** preset.

## SDK dependencies

IMPORTANT: All of this deps (except ONNX) will be removed in v1.2.0

1. Retrofit: 
```kotlin
implementation "com.squareup.retrofit2:retrofit:$version"
implementation "com.squareup.retrofit2:converter-gson:$version"
implementation "com.squareup.okhttp3:logging-interceptor:$version"
implementation "com.squareup.okhttp3:okhttp-tls:$version"
```
2. Gson: 
```kotlin
implementation "com.google.code.gson:gson:$version"
```
3. Onnx runtime
```kotlin
implementation "com.microsoft.onnxruntime:onnxruntime-android:1.15.0"
```
4. DataStore
```kotlin
implementation "androidx.datastore:datastore-preferences:$version"
```

## Usage

1. Add Audio SDK to your project as dependency
2. Initialize SDK by AudioEffectsSDK.initialize() method
3. Use AudioEffectsSDK.getAudioPipelineFactory() to get pipeline factory
4. Create pipeline
5. Use pipeline.process() method for you audio samples

The input samples could be any size, but must have 1 channel and 16000Hz frequency.
Sample will be saved in input circular buffer. When buffer size will be more or equal to pipeline batch size, it would be processed.

## Code example

SDK initialization

```
AudioEffectsSDK.initialize(this.applicationContext, YOUR_CUSTOMER_ID) { licenseStatus ->
			val status = when (licenseStatus) {
				// Place your code here
			}
		}
```

SDK usage

```
val pipelineOptions = PipelineOptions(Preset.SPEED, 2)
val factory = AudioEffectsSDK.getAudioPipelineFactory()
val audioPipeline = factory.createAudioPipeline(context, pipelineOptions)

audioPipeline.setCallback { outputBuffer ->
		// Place your code here
	}

```

You can use android SDK classes to get audio data, for example

```
AudioRecord.Builder()
    .setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION)
    .setAudioFormat(
        AudioFormat.Builder()
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setSampleRate(16000)
            .setChannelMask(AudioFormat.CHANNEL_IN_MONO)
            .build()
	)
    .setBufferSizeInBytes(YOUR_BUFFER_SIZE)
    .build()
```

## Obtaining Effects SDK Customer ID

Effects SDK Customer ID is required to get SDK working.

To receive a new trial Customer ID please fill in the contact form on [effectssdk.com](https://effectssdk.com/request-trial) website.

## Technical Details

- CUSTOMER_ID should be provided to the SDK constructor.
- SDK has 2 speed/quality presets (different ML models).

## Features

- AI Denoise - **implemented**
- Echo Cancellation - **implemented**
- AI Denoise by Speaker Extraction - **in progress**

