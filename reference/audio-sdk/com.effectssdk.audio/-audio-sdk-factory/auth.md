//[audio-sdk](../../../index.md)/[com.effectssdk.audio](../index.md)/[AudioSdkFactory](index.md)/[auth](auth.md)

# auth

[androidJvm]\
abstract fun [auth](auth.md)(customerId: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), callback: ([LicenseStatus](../-license-status/index.md)) -&gt; [Unit](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unit/index.html))

Authenticates SDK instance

#### Parameters

androidJvm

| | |
|---|---|
| customerId | Unique client identifier |
| callback | License status callback |

[androidJvm]\
abstract fun [auth](auth.md)(customerId: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), url: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html), callback: ([LicenseStatus](../-license-status/index.md)) -&gt; [Unit](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-unit/index.html))

Authenticates SDK instance

#### Parameters

androidJvm

| | |
|---|---|
| customerId | Unique client identifier |
| url | License server url |
| callback | License status callback |

[androidJvm]\
abstract fun [auth](auth.md)(localKey: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)): [LicenseStatus](../-license-status/index.md)

Authenticates SDK instance

#### Parameters

androidJvm

| | |
|---|---|
| localKey | Unique client identifier |