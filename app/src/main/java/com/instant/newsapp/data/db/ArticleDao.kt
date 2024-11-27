package com.instant.newsapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.instant.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * The logic I used for database management here is to minimize resources as much as possible since we are still working on a small, simple application.
 * However, in the case of larger projects, it would be necessary to use separate tables for each mode (e.g., two tables: articles and favorite_articles).
 */
@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteNew(article: Article): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNews(articles: List<Article>)

    @Query("SELECT * FROM articles WHERE isFavorite = 0")
    fun getNewsFromDb(): Flow<List<Article>>

    @Query("SELECT * FROM articles WHERE isFavorite = 1")
    fun getFavoriteNews(): Flow<List<Article>>

    @Delete
    suspend fun deleteNew(article: Article)

    @Query("DELETE FROM articles WHERE isFavorite = 0")
    suspend fun deleteAllNews()
}