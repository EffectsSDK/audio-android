# Class reference

## AudioEffectsSDK

#### _fun initialize()_

Call this method to Initialize EffectsSDK. Application context or activity could be passed as parameter.

| Parameter name     | Description                                                        |
|--------------------|--------------------------------------------------------------------|
| context: Context   | Application context                                                |
| customerID: String | Your customer id                                                   |
| url: Url?          | License server URL. We use our own server by default.              |
| callback: Fun      | Initialization callback function. You can handle init result here. |

#### _fun getVersionName()_

Return SDK version name as "Major.Minor.Patch.Build" string.

#### _fun isIdkInitialized()_

Return true if SDK initialized and false if not.

| Parameter name | Parameter type | Description         |
|----------------|----------------|---------------------|
| context        | Context        | Application context |

#### _fun getAudioPipelineFactory()_

Return pipeline factory instance.

## AudioPipelineFactory

#### _fun createAudioPipeline()_

Return pipeline factory instance.

## AudioPipelineOptions

| Field           | Description                                        |
|-----------------|----------------------------------------------------|
| preset: Preset  | Pipeline preset. Could be SPEED or QUALITY for now |
| numThreads: Int | Number of threads for audio process. 1 by default. |

## AudioPipeline

#### _fun process(audioSampleArray)_

| Parameter name              | Description                                                   |
|-----------------------------|---------------------------------------------------------------|
| audioSampleArray: ByteArray | Audio sample for pipeline. One channel (mono), 16000Hz format |

Return pipeline factory instance.

#### _fun stop()_

Stop pipeline. Audio buffer won't be cleaned.

#### _fun setCallback(callback)_

| Parameter name                  | Description                                                                |
|---------------------------------|----------------------------------------------------------------------------|
| callback: AudioPipelineCallback | Callback function for audio pipeline. You can handle processed audio here. |

Set callback for pipeline.

#### _fun clearBuffer()_

Clean circular input buffer for pipeline

#### _fun release()_

Release pipeline, clear pipeline buffers.

## AudioPipelineCallback

#### _fun onProcessed()_

You can handle audio sample without noises in this callback method .