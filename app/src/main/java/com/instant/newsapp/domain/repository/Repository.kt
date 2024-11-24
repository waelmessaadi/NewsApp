package com.instant.newsapp.domain.repository

import com.instant.newsapp.domain.model.Article

interface Repository {
    suspend fun getNews(): List<Article>
}