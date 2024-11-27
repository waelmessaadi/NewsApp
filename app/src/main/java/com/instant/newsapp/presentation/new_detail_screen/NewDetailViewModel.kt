package com.instant.newsapp.presentation.new_detail_screen

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instant.newsapp.R
import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.usecases.NewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewDetailViewModel @Inject constructor(
    private val newUseCase: NewUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = mutableStateOf(NewDetailState())
    val state: State<NewDetailState> get() = _state

    fun toggleFavorite(article: Article) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                newUseCase.insertFavoriteNew(article)
                _state.value = _state.value.copy(
                    isLoading = false,
                    isFavorite = true,
                    successMessage = context.getString(R.string.new_favorite_successfully)
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to favorite article: ${e.message}"
                )

            }
        }
    }
}

