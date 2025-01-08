package com.shijo.newsapp.data.api

import com.shijo.newsapp.data.models.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String, @Query("pageSize") pageSize : Int = 50): TopHeadlinesResponse
}