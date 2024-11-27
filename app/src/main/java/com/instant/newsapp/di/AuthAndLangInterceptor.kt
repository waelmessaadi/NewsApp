package com.instant.newsapp.di

import com.instant.newsapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale

/**
 * Interceptor for adding authentication and language parameters to Api request.
 */
class AuthAndLangInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val language = Locale.getDefault().language
        val apiKey = BuildConfig.API_KEY

        val originalUrl: HttpUrl = chain.request().url
        val newUrl: HttpUrl = originalUrl.newBuilder()
            .addQueryParameter("language", language)
            .addQueryParameter("apiKey", apiKey)
            .build()

        val newRequest = chain.request().newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}