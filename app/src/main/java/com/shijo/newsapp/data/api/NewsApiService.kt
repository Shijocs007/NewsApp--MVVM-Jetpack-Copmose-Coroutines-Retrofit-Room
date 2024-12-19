package com.shijo.newsapp.data.api

import com.shijo.newsapp.data.models.TopHeadlinesResponse
import com.shijo.newsapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiService {

    @Headers("X-Api-Key: $API_KEY", "User-Agent: com.shijo.newsapp/1.0")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String, @Query("pageSize") pageSize : Int = 50): TopHeadlinesResponse
}