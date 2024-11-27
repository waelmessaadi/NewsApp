package com.instant.newsapp.presentation.favorite_news_list_screen

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instant.newsapp.R
import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.usecases.GetFavoriteNews
import com.instant.newsapp.domain.usecases.NewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
open class FavoriteNewsListViewModel @Inject constructor(
    private val newUseCase: NewUseCase,
    private val getFavoriteNews: GetFavoriteNews, // For Testing
    @ApplicationContext private val context: Context) : ViewModel() {

    private val _state = mutableStateOf(FavoriteNewsListState())
    val state: State<FavoriteNewsListState> = _state

    init {
        getSavedNews()
    }

    private fun getSavedNews() {
        getFavoriteNews()
            .onEach { result ->
                when {
                    result.isNotEmpty() -> {
                        _state.value = FavoriteNewsListState(news = result)
                    }
                    else -> {
                        _state.value = FavoriteNewsListState(error = context.getString(R.string.no_favorite_news_founded))
                    }
                }
            }
            .launchIn(viewModelScope)
    }

}