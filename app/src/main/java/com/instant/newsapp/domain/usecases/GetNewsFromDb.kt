package com.instant.newsapp.domain.usecases

import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Get saved news from table 'articles' with value false of attribute 'isFavorite'
 */
class GetNewsFromDb @Inject constructor(private val repository: Repository) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getNewsFromDb()
    }
}