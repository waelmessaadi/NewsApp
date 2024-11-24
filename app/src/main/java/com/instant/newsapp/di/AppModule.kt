package com.instant.newsapp.di

import com.instant.newsapp.data.remote.ApiService
import com.instant.newsapp.data.repository.RepositoryImpl
import com.instant.newsapp.domain.repository.Repository
import com.instant.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideNewsRepository(
        api: ApiService
    ) : Repository {
        return RepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthAndLangInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsApi(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}