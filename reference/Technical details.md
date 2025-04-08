# Technical details

## Managing audio processing

### Latency mode

The SDK supports 3 [operation modes](audio-sdk/com.effectssdk.audio.pipeline/-latency-mode/index.md):

* File
* Streaming
* Playback

Streaming and playback require pre-filling the internal buffers with empty data to avoid artifacts during playback.
SDK do it automatically, but output have some delay because of it.

### Processing

To process data, call the process() method and pass your audio data array to it.
When using streaming and playback operation modes, a buffer of the same size will be returned due to internal caching.
For file processing, the output size will be a multiple of the chunk size (480 for float and 840 for PCM16).
To retrieve all cached data, call the flush() method.