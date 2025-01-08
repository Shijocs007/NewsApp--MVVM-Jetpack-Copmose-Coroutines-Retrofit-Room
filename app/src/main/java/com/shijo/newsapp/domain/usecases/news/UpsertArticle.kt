package com.shijo.newsapp.domain.usecases.news

import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.domain.repository.NewsRepository

class UpsertArticle(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(article: Article) {
        return newsRepository.upsertArticle(article = article)
    }
}