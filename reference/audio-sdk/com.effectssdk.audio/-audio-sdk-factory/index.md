//[audio-sdk](../../../index.md)/[com.effectssdk.audio](../index.md)/[AudioSdkFactory](index.md)

# AudioSdkFactory

[androidJvm]\
interface [AudioSdkFactory](index.md) : [Releasable](../../com.effectssdk.audio.pipeline/-releasable/index.md)

Main entry point for SDK operations

## Functions

| Name                                                                  | Summary                                                                                                                                                                                                                                                                                                                 |
|-----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [auth](auth.md)                                                       | [androidJvm]<br>abstract fun [auth](auth.md)(customerId: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), callback: ([LicenseStatus](../-license-status/index.md)) -&gt; [Unit](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unit/index.html))<br>Authenticates SDK instance |
| [createAudioPipeline](create-audio-pipeline.md)                       | [androidJvm]<br>abstract fun [createAudioPipeline](create-audio-pipeline.md)(options: [PipelineConfig](../../com.effectssdk.audio.pipeline/-pipeline-config/index.md)): [AudioPipeline](../../com.effectssdk.audio.pipeline/-audio-pipeline/index.md)<br>Creates configured audio pipeline                              |
| [release](../../com.effectssdk.audio.pipeline/-releasable/release.md) | [androidJvm]<br>abstract fun [release](../../com.effectssdk.audio.pipeline/-releasable/release.md)()<br>Releases all allocated resources                                                                                                                                                                                |