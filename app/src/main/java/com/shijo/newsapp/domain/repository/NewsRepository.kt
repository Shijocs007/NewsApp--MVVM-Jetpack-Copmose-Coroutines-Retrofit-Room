package com.shijo.newsapp.domain.repository

import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.data.models.Country
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getTopHeadlines(country: String): Flow<List<Article>>

    fun getSelectedCountry() : Country
}