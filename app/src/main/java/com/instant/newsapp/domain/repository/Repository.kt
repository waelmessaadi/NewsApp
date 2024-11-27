package com.instant.newsapp.domain.repository

import com.instant.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 *  Interface defining repository operations for the domain layer.
 *  Acts as an abstraction to decouple the domain layer from data sources (API, database).
 */
interface Repository {
    suspend fun getNewsFromApi(): List<Article>

    suspend fun insertFavoriteNew(article: Article): Long

    suspend fun upsertNews(allArticles: List<Article>)

    fun getNewsFromDb(): Flow<List<Article>>

    fun getFavoriteNews(): Flow<List<Article>>

    suspend fun deleteNew(article: Article)

    suspend fun deleteAllNews()

    suspend fun syncNews()
}