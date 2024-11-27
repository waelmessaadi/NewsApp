package com.instant.newsapp.presentation.favorite_news_list_screen

import com.instant.newsapp.domain.model.Article

data class FavoriteNewsListState (
    val isLoading: Boolean = false,
    val news: List<Article> = emptyList(),
    val error: String = ""
)