package com.instant.newsapp.presentation.new_detail_screen

data class NewDetailState(
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)