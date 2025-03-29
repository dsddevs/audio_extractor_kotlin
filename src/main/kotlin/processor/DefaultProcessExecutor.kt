package org.example.processor

class DefaultProcessExecutor : ProcessExecutor {
    override fun execute(command: List<String>): ProcessResult {
        val process = ProcessBuilder(command)
            .redirectErrorStream(true)
            .start()

        val sb = StringBuilder()
        process.inputStream.bufferedReader().use { reader ->
            reader.forEachLine {
                sb.append(it).append("\n")
            }
        }

        val exitCode = process.waitFor()
        return ProcessResult(exitCode, sb.toString())
    }
}