package com.instant.newsapp.domain.repository

import com.instant.newsapp.domain.model.Article

class FakeRepository : Repository {
    override suspend fun getNews(): List<Article> {
        return listOf(
            Article("Fake Description 1", "Fake Title 1", "https://fakeurl.com/1", "https://fakeurl.com/img1"),
            Article("Fake Description 2", "Fake Title 2", "https://fakeurl.com/2", "https://fakeurl.com/img2")
        )
    }
}