package com.instant.newsapp.di

import com.instant.newsapp.data.remote.ApiService
import com.instant.newsapp.data.repository.RepositoryImpl
import com.instant.newsapp.domain.repository.FakeRepository
import com.instant.newsapp.domain.repository.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.junit.Assert.assertNotNull
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModuleTest {

    private val mockBaseUrl = "https://mockapi.com/"

    @Test
    fun testProvideOkHttpClient() {
        val client = OkHttpClient.Builder().build()
        assertNotNull(client)
    }

    @Test
    fun testProvideNewsApi() {
        val client = OkHttpClient.Builder().build()
        val apiService = Retrofit.Builder()
            .baseUrl(mockBaseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
        assertNotNull(apiService)
    }

    @Test
    fun testProvideFakeNewsRepository() {
        val fakeRepository: Repository = FakeRepository()
        assertNotNull(fakeRepository)
    }
}
