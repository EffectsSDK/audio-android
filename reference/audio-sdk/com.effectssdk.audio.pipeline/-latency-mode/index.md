//[audio-sdk](../../../index.md)/[com.effectssdk.audio.pipeline](../index.md)/[LatencyMode](index.md)

# LatencyMode

[androidJvm]\
enum [LatencyMode](index.md) : [Enum](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-enum/index.html)&lt;[LatencyMode](index.md)&gt;

Represents audio processing latency modes

## Entries

|                                          |                                                                                                                     |
|------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| [FILE](-f-i-l-e/index.md)                | [androidJvm]<br>[FILE](-f-i-l-e/index.md)<br>File processing mode (lowest latency)                                  |
| [STREAMING](-s-t-r-e-a-m-i-n-g/index.md) | [androidJvm]<br>[STREAMING](-s-t-r-e-a-m-i-n-g/index.md)<br>Real-time streaming mode (balanced latency/performance) |
| [PLAYBACK](-p-l-a-y-b-a-c-k/index.md)    | [androidJvm]<br>[PLAYBACK](-p-l-a-y-b-a-c-k/index.md)<br>Live playback optimization mode                            |

## Properties

| Name                                                                      | Summary                                                                                                                                                                                                                                                                           |
|---------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [entries](entries.md)                                                     | [androidJvm]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.enums/-enum-entries/index.html)&lt;[LatencyMode](index.md)&gt;<br>Returns a representation of an immutable list of all enum entries, in the order they're declared. |
| [name](-p-l-a-y-b-a-c-k/index.md#-372974862%2FProperties%2F1159088794)    | [androidJvm]<br>val [name](-p-l-a-y-b-a-c-k/index.md#-372974862%2FProperties%2F1159088794): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)                                                                                                     |
| [ordinal](-p-l-a-y-b-a-c-k/index.md#-739389684%2FProperties%2F1159088794) | [androidJvm]<br>val [ordinal](-p-l-a-y-b-a-c-k/index.md#-739389684%2FProperties%2F1159088794): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)                                                                                                        |

## Functions

| Name                   | Summary                                                                                                                                                                                                                                                                                                                                                                           |
|------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [valueOf](value-of.md) | [androidJvm]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [LatencyMode](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md)    | [androidJvm]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-array/index.html)&lt;[LatencyMode](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared.                                                                                                                          |