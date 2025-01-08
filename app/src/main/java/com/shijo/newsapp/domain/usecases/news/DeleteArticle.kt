package com.shijo.newsapp.domain.usecases.news

import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.domain.repository.NewsRepository

class DeleteArticle(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(article: Article) {
        return newsRepository.deleteArticle(article = article)
    }
}