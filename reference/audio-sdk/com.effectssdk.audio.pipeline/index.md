//[audio-sdk](../../index.md)/[com.effectssdk.audio.pipeline](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [AudioPipeline](-audio-pipeline/index.md) | [androidJvm]<br>interface [AudioPipeline](-audio-pipeline/index.md) : [Releasable](-releasable/index.md)<br>Core audio processing interface |
| [InputType](-input-type/index.md) | [androidJvm]<br>enum [InputType](-input-type/index.md) : [Enum](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-enum/index.html)&lt;[InputType](-input-type/index.md)&gt; <br>Defines supported audio input formats |
| [LatencyMode](-latency-mode/index.md) | [androidJvm]<br>enum [LatencyMode](-latency-mode/index.md) : [Enum](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-enum/index.html)&lt;[LatencyMode](-latency-mode/index.md)&gt; <br>Represents audio processing latency modes |
| [PipelineConfig](-pipeline-config/index.md) | [androidJvm]<br>data class [PipelineConfig](-pipeline-config/index.md)(val sampleRate: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) = 48000, val numberOfChannels: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html) = 1, val dataType: [InputType](-input-type/index.md) = InputType.ENCODING_PCM_FLOAT, val latencyMode: [LatencyMode](-latency-mode/index.md) = LatencyMode.STREAMING, val floatPcmMinValue: [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html) = -1f, val floatPcmMaxValue: [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html) = 1.0f)<br>Configuration container for audio pipeline |
| [Releasable](-releasable/index.md) | [androidJvm]<br>interface [Releasable](-releasable/index.md)<br>Marks objects requiring resource cleanup |