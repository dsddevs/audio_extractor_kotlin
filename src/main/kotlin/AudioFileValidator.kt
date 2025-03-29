package org.example

import java.io.File

class AudioFileValidator {

    fun validateFile(path: String, name: String): Boolean {
        val file = File(path)
        if (!file.exists()) {
            println("❌ $name not found: ${file.absolutePath}")
            return false
        }
        println("✅ $name: ${file.absolutePath}")
        return true
    }
}