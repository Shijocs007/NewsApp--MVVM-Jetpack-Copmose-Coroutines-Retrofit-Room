package com.shijo.newsapp.domain.usecases.news

import com.shijo.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import me.amitshekhar.newsapp.data.model.Article

class GetTopHeadLines(private val newsRepository: NewsRepository) {

    operator fun invoke(country : String) : Flow<List<Article>> {
        return newsRepository.getTopHeadlines(country = country)
    }
}