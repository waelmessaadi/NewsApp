package com.instant.newsapp.presentation

/**
 * Defining navigation routes for all screens
*/
sealed class Screen(val route: String) {
    data object NewsListScreen: Screen("news_list_screen")
    data object FavoriteNewsListScreen: Screen("favorite_news_list_screen")
    data object NewDetailScreen : Screen("new_detail_screen/{article}")
}