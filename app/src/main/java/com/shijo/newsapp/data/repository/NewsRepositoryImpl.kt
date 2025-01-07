package com.shijo.newsapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.shijo.newsapp.data.api.NewsApiService
import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.data.models.Country
import com.shijo.newsapp.data.models.defaultCountry
import com.shijo.newsapp.data.room.NewsDao
import com.shijo.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val newsDao: NewsDao
) : NewsRepository {

    override fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(newsApiService.getTopHeadlines(country))
        }.map {
            it.articles.filter {article->
                !article.title.isNullOrEmpty()
                        && !article.description.isNullOrEmpty()
                        && !article.imageUrl.isNullOrEmpty()
            }
        }
    }

    override suspend fun getSelectedCountry(): Country {
        return newsDao.getSelectedCountry() ?:defaultCountry
    }
}