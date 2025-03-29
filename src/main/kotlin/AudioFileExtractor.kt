package org.example

import org.example.processor.ProcessExecutor
import org.example.processor.DefaultProcessExecutor
import java.io.File
import java.io.IOException

/**
 * Class for extracting audio from video files using FFmpeg.
 * Allows you to convert a video to a WAV audio file with the specified parameters.
 */
class AudioFileExtractor (
    private val processExecutor: ProcessExecutor = DefaultProcessExecutor()
) {

    private var audioFile: File? = null

    fun extractAudio(videoFilePath: String): Boolean {
        return try {
            val handledVideoFilePath = videoFilePath.trim('"')
            val videoFile = File(handledVideoFilePath)

            if (!videoFile.exists()) {
                throw IOException("Video file not found: ${videoFile.absolutePath}")
            }

            // Conversation process: video file (.mkv format) to audio file (.wav format))
            audioFile = File(
                videoFile.parentFile,
                "${videoFile.nameWithoutExtension}.wav"
            )

            val command = listOf(
                "ffmpeg",
                "-y",
                "-i", videoFile.absolutePath,
                "-vn",                    // turn off video
                "-acodec",
                "pcm_s16le",              // pcm_s16le - for Wav format
                "-ar", "16000",           // sampling frequency 16000 Hz
                "-ac", "1",               // audio channel (mono)
                audioFile!!.absolutePath
            )

            val result = processExecutor.execute(command)

            if (result.exitCode == 0) {
                return true
            } else {
                throw IOException("Error: Exit code failure: ${result.exitCode}")
            }

        } catch (e: Exception) {
            audioFile = null
            false
        }
    }

    fun getAudioFilePath(): String {
        return audioFile?.absolutePath
            ?: throw IllegalStateException("Error: Audio file path not available")
    }

    fun hasAudioFile(): Boolean {
        return audioFile?.exists() ?: false
    }

}