package com.instant.newsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.instant.newsapp.domain.model.Article

@Database(entities = [Article::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract val articleDao: ArticleDao

    companion object {
        const val DATABASE_NAME = "articles_db"
    }
}