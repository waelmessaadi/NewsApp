package com.instant.newsapp.data.remote.mapper

import com.instant.newsapp.data.remote.dto.ArticleDto
import com.instant.newsapp.domain.model.Article

fun ArticleDto.toDomainModel(): Article {
    return Article(
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
    )
}

fun List<ArticleDto>.toDomainModels(): List<Article> {
    return this.map { it.toDomainModel() }
}
