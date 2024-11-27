package com.instant.newsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.presentation.favorite_news_list_screen.FavoriteNewsListScreen
import com.instant.newsapp.presentation.news_list_screen.NewsListScreen
import com.instant.newsapp.presentation.new_detail_screen.NewDetailScreen
import com.instant.newsapp.presentation.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            // Setting up the navigation graph for the News app with screens for news list, favorite news, and article details.
            NewsAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NewsListScreen.route
                    ) {
                        // List news composable route
                        composable(
                            route = Screen.NewsListScreen.route
                        ) {
                            NewsListScreen(navController)
                        }

                        // Favorite list composable route
                        composable(
                            route = Screen.FavoriteNewsListScreen.route
                        ) {
                            FavoriteNewsListScreen(navController)
                        }

                        // Detail new screen composable route
                        composable(
                            route = Screen.NewDetailScreen.route,
                            arguments = listOf(navArgument("article") { type = NavType.StringType })
                        ) { backStackEntry ->

                            val articleJson = backStackEntry.arguments?.getString("article")
                            val article = articleJson?.let { json ->
                                Gson().fromJson(json, Article::class.java)
                            }
                            article?.let {
                                NewDetailScreen(
                                    article = articleJson, navController = navController
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}
