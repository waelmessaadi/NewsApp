package com.instant.newsapp.util

import java.io.InputStreamReader
import java.nio.charset.Charset

class MockResponseFileReader(private val fileName: String) {

    val content: String
        get() {
            val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
                ?: throw IllegalArgumentException("File not found: $fileName")
            val reader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
            return reader.readText().also {
                reader.close()
            }
        }
}
