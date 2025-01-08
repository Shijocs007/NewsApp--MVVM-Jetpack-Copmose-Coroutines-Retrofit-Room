package com.shijo.newsapp.domain.repository

import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.data.models.Country
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getTopHeadlines(country: String): Flow<List<Article>>

    suspend fun getSelectedCountry() : Country

    suspend fun getSavedArticle(id: String) : Article?

    suspend fun deleteArticle(article: Article)

    suspend fun upsertArticle(article: Article)
}