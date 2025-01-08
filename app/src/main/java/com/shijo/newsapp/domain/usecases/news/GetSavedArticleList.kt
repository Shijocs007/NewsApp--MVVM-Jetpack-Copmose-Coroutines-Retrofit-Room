package com.shijo.newsapp.domain.usecases.news

import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedArticleList(private val newsRepository: NewsRepository) {

    operator fun invoke() : Flow<List<Article>> {
        return newsRepository.getSavedArticleList()
    }
}