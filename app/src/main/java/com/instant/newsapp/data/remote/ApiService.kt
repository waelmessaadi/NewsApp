package com.instant.newsapp.data.remote

import com.instant.newsapp.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
    suspend fun getNewsList(@Query("q") alias: String = "all"): NewsResponseDto

}