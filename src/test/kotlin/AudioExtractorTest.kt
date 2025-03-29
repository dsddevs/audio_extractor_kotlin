package org.example

import org.example.processor.ProcessExecutor
import org.example.processor.ProcessResult
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AudioFileExtractorTest {

    class FakeSuccessProcessExecutor : ProcessExecutor {
        override fun execute(command: List<String>): ProcessResult {
            val outputFilePath = command.last()
            val file = File(outputFilePath)
            file.writeText("fake wav data")
            return ProcessResult(0, "Success")
        }
    }

    class FakeFailureProcessExecutor : ProcessExecutor {
        override fun execute(command: List<String>): ProcessResult {
            return ProcessResult(1, "Error")
        }
    }

    @Test
    fun `extractAudio should return true when ffmpeg succeeds`() {
        val extractor = AudioFileExtractor(FakeSuccessProcessExecutor())

        val tempVideo = File.createTempFile("test_video", ".mkv")
        tempVideo.writeText("dummy content")

        val result = extractor.extractAudio(tempVideo.absolutePath)

        assertTrue(result)
        assertTrue(extractor.hasAudioFile())

        val audioFilePath = extractor.getAudioFilePath()
        assertTrue(audioFilePath.endsWith(".wav"))

        // Clean up
        tempVideo.delete()
        File(audioFilePath).delete()
    }

    @Test
    fun `extractAudio should return false when ffmpeg fails`() {
        val extractor = AudioFileExtractor(FakeFailureProcessExecutor())

        val tempVideo = File.createTempFile("test_video", ".mkv")
        tempVideo.writeText("dummy content")

        val result = extractor.extractAudio(tempVideo.absolutePath)

        assertFalse(result)
        assertFalse(extractor.hasAudioFile())

        tempVideo.delete()
    }

    @Test
    fun `extractAudio should return false when file does not exist`() {
        val extractor = AudioFileExtractor(FakeSuccessProcessExecutor())

        val fakePath = "C:/this/file/does/not/exist.mkv"

        val result = extractor.extractAudio(fakePath)

        assertFalse(result)
        assertFalse(extractor.hasAudioFile())
    }
}