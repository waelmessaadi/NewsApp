package com.instant.newsapp.presentation.new_detail_screen

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import com.instant.newsapp.R
import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.presentation.ui.theme.textSizeLarge
import com.instant.newsapp.presentation.ui.theme.textSizeSmall
import kotlinx.coroutines.delay
import timber.log.Timber
import java.net.URLDecoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDetailScreen(article: String?, navController: NavController) {

    val gson = Gson()

    val articleJson = article?.let { URLDecoder.decode(it, "UTF-8") }

    Timber.e("Received JSON $articleJson")

    val newArticle = gson.fromJson(articleJson, Article::class.java)

    val context = LocalContext.current
    val openUrl: (String) -> Unit = { url ->
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.go_back)
                        )
                    }
                },
                actions = {
                    AddToFavoritesButton()
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            Text(
                text = newArticle?.title!!,
                style = textSizeLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp, bottom = 12.dp)
            )

            Image(
                painter = rememberAsyncImagePainter(
                    model = newArticle.urlToImage ?: R.drawable.ic_instant
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                text = newArticle.description ?: stringResource(id = R.string.no_description),
                style = textSizeSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            newArticle.url?.let {
                ClickableText(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    text = AnnotatedString(stringResource(id = R.string.view_more)),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    ),
                    onClick = {
                        openUrl(newArticle.url)
                    }
                )
            }
        }
    }
}

@Composable
fun AddToFavoritesButton(

) {
    var isClicked by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isClicked) 1.3f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    IconButton(
        onClick = {
            isClicked = true
        },
        modifier = Modifier.scale(scale)
    ) {
        Icon(
            imageVector = if (isClicked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = stringResource(id = R.string.animated_icon),
            tint = if (isClicked) Color.Red else Color.Gray
        )
    }

    LaunchedEffect(isClicked) {
        if (isClicked) {
            delay(200)
            isClicked = false
        }
    }
}