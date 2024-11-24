package com.instant.newsapp.data.repository

import com.instant.newsapp.data.remote.ApiService
import com.instant.newsapp.data.remote.dto.toArticleList
import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class RepositoryImpl @Inject constructor(
    private val api: ApiService,
): Repository {
    override suspend fun getNews(): List<Article> {
        val response = api.getNewsList()
        return response.toArticleList()
    }
}