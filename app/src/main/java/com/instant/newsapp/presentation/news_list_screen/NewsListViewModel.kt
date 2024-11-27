package com.instant.newsapp.presentation.news_list_screen

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instant.newsapp.domain.usecases.NewUseCase
import com.instant.newsapp.util.CommonMethods
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
open class NewsListViewModel @Inject constructor(
    private val newUseCase: NewUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    init {
        checkConnectionAndSync()
    }

    // Called by default when viewModel started
    // Also, called directly when swipeRefresh
    fun checkConnectionAndSync() {
        if (CommonMethods.isOnline(connectivityManager)) {
            syncNews()
        } else {
            getNewsFromDb()
        }
    }

    private fun syncNews() {
        viewModelScope.launch {
            _state.value = NewsListState(isLoading = true)
            try {
                newUseCase.syncNews()
                getNewsFromDb()
            } catch (e: Exception) {
                _state.value = NewsListState(error = e.message ?: "Unexpected error")
            }
        }
    }

    private fun getNewsFromDb() {
        newUseCase.getNewsFromDb()
            .onEach { result ->
                when {
                    result.isNotEmpty() -> {
                        _state.value = NewsListState(news = result)
                    }
                    else -> {
                        _state.value = NewsListState(error = "No data available")
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}