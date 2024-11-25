package com.instant.newsapp.presentation.news_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.usecases.FakeGetNewsUseCase

class FakeNewsListViewModel : NewsListViewModel(getNewsUseCase = FakeGetNewsUseCase()) {
    private val _state = mutableStateOf(NewsListState())
    override val state: State<NewsListState> = _state

    override fun getNews() {
        _state.value = NewsListState(news = listOf(
            Article("Description 1", "Title 1", "url1", "urlToImage1"),
            Article( "Description 2","Title 2", "url2", "urlToImage2")
        ))
    }
}
