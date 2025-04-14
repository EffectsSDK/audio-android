//[audio-sdk](../../../index.md)/[com.effectssdk.audio.pipeline](../index.md)/[AudioPipeline](index.md)

# AudioPipeline

[androidJvm]\
interface [AudioPipeline](index.md) : [Releasable](../-releasable/index.md)

Core audio processing interface

## Functions

| Name | Summary |
|---|---|
| [enableNoiseSuppression](enable-noise-suppression.md) | [androidJvm]<br>abstract fun [enableNoiseSuppression](enable-noise-suppression.md)(enable: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Enables/disables noise suppression |
| [flushBytes](flush-bytes.md) | [androidJvm]<br>abstract fun [flushBytes](flush-bytes.md)(): [ByteArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-byte-array/index.html)<br>Flushes internal buffers |
| [flushFloats](flush-floats.md) | [androidJvm]<br>abstract fun [flushFloats](flush-floats.md)(): [FloatArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float-array/index.html)<br>Flushes internal buffers |
| [getLatencyMode](get-latency-mode.md) | [androidJvm]<br>abstract fun [getLatencyMode](get-latency-mode.md)(): [LatencyMode](../-latency-mode/index.md) |
| [getNoiseSuppressionPower](get-noise-suppression-power.md) | [androidJvm]<br>abstract fun [getNoiseSuppressionPower](get-noise-suppression-power.md)(): [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html) |
| [isNoiseSuppressionEnabled](is-noise-suppression-enabled.md) | [androidJvm]<br>abstract fun [isNoiseSuppressionEnabled](is-noise-suppression-enabled.md)(): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html) |
| [process](process.md) | [androidJvm]<br>abstract fun [process](process.md)(data: [ByteArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-byte-array/index.html)): [ByteArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-byte-array/index.html)<br>abstract fun [process](process.md)(data: [FloatArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float-array/index.html)): [FloatArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float-array/index.html)<br>abstract fun [process](process.md)(inputData: [ByteBuffer](https://developer.android.com/reference/kotlin/java/nio/ByteBuffer.html), inputFrameCount: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), outputData: [ByteBuffer](https://developer.android.com/reference/kotlin/java/nio/ByteBuffer.html), outputFrameCount: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Processes audio frame |
| [release](../-releasable/release.md) | [androidJvm]<br>abstract fun [release](../-releasable/release.md)()<br>Releases all allocated resources |
| [reset](reset.md) | [androidJvm]<br>abstract fun [reset](reset.md)()<br>Resets pipeline state |
| [setLatencyMode](set-latency-mode.md) | [androidJvm]<br>abstract fun [setLatencyMode](set-latency-mode.md)(mode: [LatencyMode](../-latency-mode/index.md))<br>Sets processing latency mode |
| [setNoiseSuppressionPower](set-noise-suppression-power.md) | [androidJvm]<br>abstract fun [setNoiseSuppressionPower](set-noise-suppression-power.md)(power: [Float](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float/index.html))<br>Set noise suppression power. |