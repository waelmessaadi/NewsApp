package com.instant.newsapp.domain.usecases

import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.repository.Repository
import com.instant.newsapp.util.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * To delete once new, not used right now
 */
class DeleteNew @Inject constructor(private val repository: Repository) {
    operator fun invoke(article: Article) = flow {
        try {
            emit(Resource.Loading())
            val news = repository.deleteNew(article)
            emit(Resource.Success(news))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}