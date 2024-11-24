package com.instant.newsapp.presentation.news_list_screen

import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.usecases.GetNewsUseCase
import com.instant.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetNewsUseCase : GetNewsUseCase(repository = FakeRepository()) {

    // Simuler un flow de Resource avec des données factices (succès ou erreur)
    override fun invoke(): Flow<Resource<List<Article>>> = flow {
        // On simule une réponse "succès" avec une liste d'articles factices
        emit(Resource.Loading())  // Simuler un état de chargement
        try {
            // Simuler des données fictives
            val fakeArticles = listOf(
                Article("Title 1", "Description 1", "https://example.com/1", "imageUrl1"),
                Article("Title 2", "Description 2", "https://example.com/2", "imageUrl2")
            )
            emit(Resource.Success(fakeArticles))  // Simuler une réponse de succès avec une liste d'articles
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred"))  // Simuler une erreur
        }
    }
}
