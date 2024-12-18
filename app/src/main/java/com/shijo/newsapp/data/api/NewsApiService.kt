package com.shijo.newsapp.data.api

import com.shijo.newsapp.utils.Constants.API_KEY
import me.amitshekhar.newsapp.data.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiService {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getHeadlines(@Query("country") country: String): TopHeadlinesResponse
}