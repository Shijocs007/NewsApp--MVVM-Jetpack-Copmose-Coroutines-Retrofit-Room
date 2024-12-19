package com.shijo.newsapp.domain.usecases.news

import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetTopHeadLines(private val newsRepository: NewsRepository) {

    operator fun invoke(country : String) : Flow<List<Article>> {
        return newsRepository.getTopHeadlines(country = country)
    }
}