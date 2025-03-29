# Audio Extractor

`audio_extractor` is a Kotlin library for extracting audio from video files using FFmpeg. It allows you to convert a video to a WAV audio file with specified parameters.

## Installation

To use `audio_extractor` in your project, add the following dependency to your `build.gradle` file:

```groovy
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    testImplementation(kotlin("test"))
}
```


## Dependencies

For `audio_extractor` to work, FFmpeg must be installed. You can download and install it from the [official FFmpeg website](https://ffmpeg.org/download.html).

## License

This project is licensed under the Apache-2.0 license.
