//[audio-sdk](../../../index.md)/[com.effectssdk.audio.pipeline](../index.md)/[AudioPipeline](index.md)/[process](process.md)

# process

[androidJvm]\
abstract fun [process](process.md)(data: [FloatArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float-array/index.html)): [FloatArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-float-array/index.html)

Processes audio frame

#### Return

Processed PCM float array

#### Parameters

androidJvm

| | |
|---|---|
| data | Input PCM float array |

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-illegal-state-exception/index.html) | if pipeline is released |

[androidJvm]\
abstract fun [process](process.md)(data: [ByteArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-byte-array/index.html)): [ByteArray](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-byte-array/index.html)

Processes audio frame

#### Return

Processed PCM 16 bit array

#### Parameters

androidJvm

| | |
|---|---|
| data | Input PCM 16 bit array |

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-illegal-state-exception/index.html) | if pipeline is released |

[androidJvm]\
abstract fun [process](process.md)(inputData: [ByteBuffer](https://developer.android.com/reference/kotlin/java/nio/ByteBuffer.html), inputFrameCount: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html), outputData: [ByteBuffer](https://developer.android.com/reference/kotlin/java/nio/ByteBuffer.html), outputFrameCount: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)

Processes audio frame

#### Return

Number of written frames

#### Parameters

androidJvm

| | |
|---|---|
| inputData | Input audio bytes |
| inputFrameCount | Number of input audio frames |
| outputData | Output audio bytes |
| outputFrameCount | Number of output audio frames |

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-illegal-state-exception/index.html) | if pipeline is released |