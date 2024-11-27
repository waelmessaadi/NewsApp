package com.instant.newsapp.domain.usecases

import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.repository.Repository
import javax.inject.Inject

/**
 * Insert once new in table 'articles' with 'isFavorite=true'
 */
class InsertFavoriteNew @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(article: Article) {
        repository.insertFavoriteNew(article)
    }
}