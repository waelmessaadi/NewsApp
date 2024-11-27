package com.instant.newsapp.presentation.favorite_news_list_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.instant.newsapp.R
import com.instant.newsapp.presentation.favorite_news_list_screen.component.FavoriteNewsListItem
import com.instant.newsapp.presentation.ui.theme.textSizeLarge
import timber.log.Timber
import java.net.URLEncoder

@Composable
fun FavoriteNewsListScreen(
    navController: NavController,
    viewModel: FavoriteNewsListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
    Column(
        modifier = Modifier.padding(top = 50.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.go_back),
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(24.dp),
                )
            }
            Text(
                text = stringResource(id = R.string.favorite_news),
                style = textSizeLarge,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(state.news, key = { article -> article.id }) { article ->
                FavoriteNewsListItem(
                    article = article,
                    onItemClick = {

                        val gson = Gson()
                        val articleJson = gson.toJson(article)
                        val encodedArticleJson = URLEncoder.encode(articleJson, "UTF-8")

                        navController.navigate("new_detail_screen/$encodedArticleJson")

                    }
                )
            }
        }
    }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}