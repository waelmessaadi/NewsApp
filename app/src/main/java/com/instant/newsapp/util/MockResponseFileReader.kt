package com.instant.newsapp.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.instant.newsapp.domain.model.Article
import java.io.File
import java.io.InputStreamReader
import java.nio.charset.Charset

class MockResponseFileReader(val fileName: String) {

    val content: String
        get() {
            val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
                ?: throw IllegalArgumentException("File not found: $fileName")
            val reader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
            return reader.readText().also {
                reader.close()
            }
        }

    fun loadArticlesFromFile(): List<Article> {
        val file = File("src/test/resources/$fileName")
        val json = file.readText(Charset.forName("UTF-8"))

        val type = object : TypeToken<List<Article>>() {}.type
        return Gson().fromJson(json, type)
    }
}
