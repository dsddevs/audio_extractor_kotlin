package org.example


fun main() {
    val videoFilePath = "C:\\Users\\dsd\\Downloads\\Telegram Desktop\\china.mkv"

    val audioExtractor = AudioFileExtractor()
    val audioValidator = AudioFileValidator()

    if (audioExtractor.extractAudio(videoFilePath)) {
        val audioFilePath = videoFilePath.replace(".mkv", ".wav")
        audioValidator.validateFile(audioFilePath, "audio_file")
    }
}
