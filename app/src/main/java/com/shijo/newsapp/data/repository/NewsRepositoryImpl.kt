package com.shijo.newsapp.data.repository

import com.shijo.newsapp.data.api.NewsApiService
import com.shijo.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.amitshekhar.newsapp.data.model.Article
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRepository {

    override fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(newsApiService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }
}