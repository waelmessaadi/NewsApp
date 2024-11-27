package com.instant.newsapp.presentation.news_list_screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.presentation.news_list_screen.NewsListScreen
import org.junit.Rule
import org.junit.Test

class NewsListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun newsListScreen_displaysArticlesCorrectly() {
        val fakeArticles = listOf(
            Article(1,"Description 1","Title 1",  "url1", "urlToImage1"),
            Article(2,"Description 2","Title 2",  "url2", "urlToImage2")
        )
        val fakeViewModel = null

        composeTestRule.setContent {
//            NewsListScreen(viewModel = fakeViewModel, navController = rememberNavController())
        }

        composeTestRule.onNodeWithText("Title 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Title 2").assertIsDisplayed()
    }
}
