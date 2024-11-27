package com.instant.newsapp.data.repository

import com.instant.newsapp.data.db.ArticleDao
import com.instant.newsapp.data.remote.ApiService
import com.instant.newsapp.data.remote.dto.toArticleList
import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

/**
 * Repository implementation that handles data operations.
 * It interacts with remote data sources (API) and local data sources (database)
 * to provide the necessary data to the rest of the application.
 */
@ActivityScoped
class RepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: ArticleDao
): Repository {

    // Fetch news from the API
    override suspend fun getNewsFromApi(): List<Article> {
        val response = api.getNewsList()
        return response.toArticleList()
    }

    // Insert new
    override suspend fun insertFavoriteNew(article: Article): Long {
        return dao.insertFavoriteNew(article)
    }

    // Fetch news from the API
    override suspend fun upsertNews(allArticles: List<Article>) {
        return dao.upsertNews(allArticles)
    }

    // Retrieve news locally (offline)
    override fun getNewsFromDb(): Flow<List<Article>> {
        return dao.getNewsFromDb()
    }

    // Retrieve favorite news
    override fun getFavoriteNews(): Flow<List<Article>> {
        return dao.getFavoriteNews()
    }

    // Remove new from favorites
    override suspend fun deleteNew(article: Article) {
        return dao.deleteNew(article)
    }

    // Remove new from favorites
    override suspend fun deleteAllNews() {
        return dao.deleteAllNews()
    }

    // Synchronize data (API -> Local database)
    override suspend fun syncNews() {
        deleteAllNews()
        val articles = getNewsFromApi()

        Timber.e("newsFromApi : $articles")

        upsertNews(articles)
    }

}