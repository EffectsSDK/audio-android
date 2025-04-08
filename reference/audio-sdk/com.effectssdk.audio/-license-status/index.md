//[audio-sdk](../../../index.md)/[com.effectssdk.audio](../index.md)/[LicenseStatus](index.md)

# LicenseStatus

[androidJvm]\
enum [LicenseStatus](index.md) : [Enum](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-enum/index.html)&lt;[LicenseStatus](index.md)
&gt;

Represents SDK license status

## Entries

|                                       |                                                                                |
|---------------------------------------|--------------------------------------------------------------------------------|
| [ACTIVE](-a-c-t-i-v-e/index.md)       | [androidJvm]<br>[ACTIVE](-a-c-t-i-v-e/index.md)<br>Valid active license        |
| [INACTIVE](-i-n-a-c-t-i-v-e/index.md) | [androidJvm]<br>[INACTIVE](-i-n-a-c-t-i-v-e/index.md)<br>License not activated |
| [EXPIRED](-e-x-p-i-r-e-d/index.md)    | [androidJvm]<br>[EXPIRED](-e-x-p-i-r-e-d/index.md)<br>Expired license          |
| [ERROR](-e-r-r-o-r/index.md)          | [androidJvm]<br>[ERROR](-e-r-r-o-r/index.md)<br>License verification error     |

## Properties

| Name                                                                                                                        | Summary                                                                                                                                                                                                                                                                             |
|-----------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [entries](entries.md)                                                                                                       | [androidJvm]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.enums/-enum-entries/index.html)&lt;[LicenseStatus](index.md)&gt;<br>Returns a representation of an immutable list of all enum entries, in the order they're declared. |
| [name](../../com.effectssdk.audio.pipeline/-latency-mode/-p-l-a-y-b-a-c-k/index.md#-372974862%2FProperties%2F1159088794)    | [androidJvm]<br>val [name](../../com.effectssdk.audio.pipeline/-latency-mode/-p-l-a-y-b-a-c-k/index.md#-372974862%2FProperties%2F1159088794): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)                                                     |
| [ordinal](../../com.effectssdk.audio.pipeline/-latency-mode/-p-l-a-y-b-a-c-k/index.md#-739389684%2FProperties%2F1159088794) | [androidJvm]<br>val [ordinal](../../com.effectssdk.audio.pipeline/-latency-mode/-p-l-a-y-b-a-c-k/index.md#-739389684%2FProperties%2F1159088794): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)                                                        |

## Functions

| Name                   | Summary                                                                                                                                                                                                                                                                                                                                                                             |
|------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [valueOf](value-of.md) | [androidJvm]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [LicenseStatus](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md)    | [androidJvm]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-array/index.html)&lt;[LicenseStatus](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared.                                                                                                                          |