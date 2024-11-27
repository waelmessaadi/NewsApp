package com.instant.newsapp.domain.usecases

import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.repository.FakeRepository
import com.instant.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
/*
class FakeGetNewsUseCase : GetNewsUseCase(repository = FakeRepository()) {

    override fun invoke(): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            val fakeArticles = listOf(
                Article(1,"Title 1", "Description 1", "https://example.com/1", "imageUrl1"),
                Article(2,"Title 2", "Description 2", "https://example.com/2", "imageUrl2")
            )
            emit(Resource.Success(fakeArticles))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred"))
        }
    }
}*/
