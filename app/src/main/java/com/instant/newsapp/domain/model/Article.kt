package com.instant.newsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
     val description: String?,
     val title: String?,
     val url: String?,
     val urlToImage: String?,
     val isFavorite: Boolean = false
)
