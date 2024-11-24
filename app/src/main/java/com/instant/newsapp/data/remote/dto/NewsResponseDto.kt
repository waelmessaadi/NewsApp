package com.instant.newsapp.data.remote.dto

import com.instant.newsapp.data.remote.mapper.toDomainModels
import com.instant.newsapp.domain.model.Article

data class NewsResponseDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)

fun NewsResponseDto.toArticleList(): List<Article> {
    return this.articles.toDomainModels()
}