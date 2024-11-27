package com.instant.newsapp.domain.usecases

import com.instant.newsapp.domain.model.Article
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeGetFavoriteNews(private val articles: List<Article>) : GetFavoriteNews(mockk()) {
    override fun invoke(): Flow<List<Article>> {
        return flowOf(articles)
    }
}
