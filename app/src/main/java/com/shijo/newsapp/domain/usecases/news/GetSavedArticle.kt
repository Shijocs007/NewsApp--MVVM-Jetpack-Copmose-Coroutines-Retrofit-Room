package com.shijo.newsapp.domain.usecases.news

import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.domain.repository.NewsRepository

class GetSavedArticle(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(id : String) : Article? {
        return newsRepository.getSavedArticle(id = id)
    }
}