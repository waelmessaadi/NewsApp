package com.instant.newsapp.domain.usecases

import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.repository.Repository
import com.instant.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

open class GetNewsUseCase @Inject constructor(private val repository: Repository) {
open operator fun invoke(): Flow<Resource<List<Article>>> = flow {
    try {
        emit(Resource.Loading())
        val news = repository.getNews()
        emit(Resource.Success(news))
    } catch(e: HttpException) {
        emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
    } catch(e: IOException) {
        emit(Resource.Error("Couldn't reach server. Check your internet connection."))
    }
}
}