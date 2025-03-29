package org.example.processor

interface ProcessExecutor {
    fun execute(command: List<String>): ProcessResult
}