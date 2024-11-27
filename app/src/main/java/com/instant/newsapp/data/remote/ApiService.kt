package com.instant.newsapp.data.remote

import com.instant.newsapp.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ApiService interface that defines the API endpoints for retrieving data.
 * It uses Retrofit to perform network operations and return data models.
 */
interface ApiService {

    @GET("v2/everything")
    suspend fun getNewsList(@Query("q") alias: String = "france-news"): NewsResponseDto

}