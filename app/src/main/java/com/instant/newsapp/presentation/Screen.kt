package com.instant.newsapp.presentation

sealed class Screen(val route: String) {
    object NewsListScreen: Screen("news_list_screen")
    object NewDetailScreen : Screen("new_detail_screen/{article}")
}