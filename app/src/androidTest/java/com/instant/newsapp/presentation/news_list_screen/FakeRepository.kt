package com.instant.newsapp.presentation.news_list_screen

import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.repository.Repository

class FakeRepository : Repository {
    override suspend fun getNews(): List<Article> {
        return listOf(
            Article("Fake Description 1", "Fake Title 1", "https://fakeurl.com/1", "https://fakeurl.com/img1"),
            Article("Fake Description 2", "Fake Title 2", "https://fakeurl.com/2", "https://fakeurl.com/img2")
        )
    }
}