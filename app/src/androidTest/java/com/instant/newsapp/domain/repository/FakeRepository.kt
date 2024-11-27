package com.instant.newsapp.domain.repository

import com.instant.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class FakeRepository : Repository {
    override suspend fun getNewsFromApi(): List<Article> {
        return listOf(
            Article(1,"Fake Description 1", "Fake Title 1", "https://fakeurl.com/1", "https://fakeurl.com/img1"),
            Article(2,"Fake Description 2", "Fake Title 2", "https://fakeurl.com/2", "https://fakeurl.com/img2")
        )
    }

    override suspend fun insertFavoriteNew(article: Article): Long {
        TODO("Not yet implemented")
    }

    override suspend fun upsertNews(allArticles: List<Article>) {
        TODO("Not yet implemented")
    }

    override fun getNewsFromDb(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNew(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllNews() {
        TODO("Not yet implemented")
    }

    override suspend fun syncNews() {
        TODO("Not yet implemented")
    }
}