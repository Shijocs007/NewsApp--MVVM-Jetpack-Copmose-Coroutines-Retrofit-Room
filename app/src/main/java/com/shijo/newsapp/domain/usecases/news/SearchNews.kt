package com.shijo.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNews @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<Article>> {
        return newsRepository.searchNews(
            searchQuery = searchQuery,
        )
    }
}