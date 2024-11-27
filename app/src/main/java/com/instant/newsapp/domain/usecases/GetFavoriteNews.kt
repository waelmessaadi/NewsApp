package com.instant.newsapp.domain.usecases

import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Get favorites news: news saved in table 'articles' with value true of attribute 'isFavorite'
 */
open class GetFavoriteNews @Inject constructor(private val repository: Repository) {
    open operator fun invoke(): Flow<List<Article>> {
        return repository.getFavoriteNews()
    }
}