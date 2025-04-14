//[audio-sdk](../../../index.md)/[com.effectssdk.audio.pipeline](../index.md)/[PipelineConfig](index.md)

# PipelineConfig

[androidJvm]\
data class [PipelineConfig](index.md)(val sampleRate: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) = 48000, val numberOfChannels: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) = 1, val dataType: [InputType](../-input-type/index.md) = InputType.ENCODING_PCM_FLOAT, val latencyMode: [LatencyMode](../-latency-mode/index.md) = LatencyMode.STREAMING, val floatPcmMinValue: [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html) = -1f, val floatPcmMaxValue: [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html) = 1.0f)

Configuration container for audio pipeline

## Constructors

| | |
|---|---|
| [PipelineConfig](-pipeline-config.md) | [androidJvm]<br>constructor(sampleRate: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) = 48000, numberOfChannels: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) = 1, dataType: [InputType](../-input-type/index.md) = InputType.ENCODING_PCM_FLOAT, latencyMode: [LatencyMode](../-latency-mode/index.md) = LatencyMode.STREAMING, floatPcmMinValue: [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html) = -1f, floatPcmMaxValue: [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html) = 1.0f) |

## Properties

| Name | Summary |
|---|---|
| [dataType](data-type.md) | [androidJvm]<br>val [dataType](data-type.md): [InputType](../-input-type/index.md)<br>Input audio format (default ENCODING_PCM_FLOAT) |
| [floatPcmMaxValue](float-pcm-max-value.md) | [androidJvm]<br>val [floatPcmMaxValue](float-pcm-max-value.md): [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html) = 1.0f |
| [floatPcmMinValue](float-pcm-min-value.md) | [androidJvm]<br>val [floatPcmMinValue](float-pcm-min-value.md): [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html) |
| [latencyMode](latency-mode.md) | [androidJvm]<br>val [latencyMode](latency-mode.md): [LatencyMode](../-latency-mode/index.md)<br>Processing latency mode (default STREAMING) |
| [numberOfChannels](number-of-channels.md) | [androidJvm]<br>val [numberOfChannels](number-of-channels.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) = 1<br>Number of audio channels (default 1) |
| [sampleRate](sample-rate.md) | [androidJvm]<br>val [sampleRate](sample-rate.md): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) = 48000<br>Audio sampling rate in Hz (default 48000) |