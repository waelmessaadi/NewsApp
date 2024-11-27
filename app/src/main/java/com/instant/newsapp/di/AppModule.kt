package com.instant.newsapp.di

import android.app.Application
import androidx.room.Room
import com.instant.newsapp.data.db.AppDatabase
import com.instant.newsapp.data.remote.ApiService
import com.instant.newsapp.data.repository.RepositoryImpl
import com.instant.newsapp.domain.repository.Repository
import com.instant.newsapp.domain.usecases.NewUseCase
import com.instant.newsapp.domain.usecases.DeleteNew
import com.instant.newsapp.domain.usecases.GetFavoriteNews
import com.instant.newsapp.domain.usecases.GetNewsFromDb
import com.instant.newsapp.domain.usecases.SyncNews
import com.instant.newsapp.domain.usecases.InsertFavoriteNew
import com.instant.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This is the central module for dependency injection.
 * It provides the necessary instances for the application, such as the API service, repositories, and other shared dependencies.
 * All dependencies are provided as singletons to ensure that there is only one instance throughout the application lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsRepository(
        api: ApiService,
        db: AppDatabase
    ) : Repository {
        return RepositoryImpl(api, db.articleDao)
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

    @Provides
    @Singleton
    fun provideArticleUseCases(repository: Repository): NewUseCase {
        return NewUseCase(
            getNewsFromDb = GetNewsFromDb(repository),
            getFavoriteNews = GetFavoriteNews(repository),
            deleteNew = DeleteNew(repository),
            insertFavoriteNew = InsertFavoriteNew(repository),
            syncNews = SyncNews(repository)
        )
    }
}