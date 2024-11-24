package com.instant.newsapp.presentation.news_list_screen

import com.instant.newsapp.data.remote.dto.ArticleDto
import com.instant.newsapp.domain.model.Article

data class NewsListState (
    val isLoading: Boolean = false,
    val news: List<Article> = emptyList(),
    val error: String = ""
)