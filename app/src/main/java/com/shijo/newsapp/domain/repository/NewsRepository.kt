package com.shijo.newsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import me.amitshekhar.newsapp.data.model.Article

interface NewsRepository {

    fun getTopHeadlines(country: String): Flow<List<Article>>
}