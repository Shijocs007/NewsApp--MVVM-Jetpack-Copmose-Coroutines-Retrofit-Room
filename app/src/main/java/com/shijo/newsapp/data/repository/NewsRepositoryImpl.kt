package com.shijo.newsapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shijo.newsapp.data.api.NewsApiService
import com.shijo.newsapp.data.api.NewsSearchPagingSource
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

    override fun searchNews(searchQuery: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsSearchPagingSource(
                    api = newsApiService,
                    searchQuery = searchQuery
                )
            }
        ).flow
    }

    override suspend fun getSelectedCountry(): Country {
        return newsDao.getSelectedCountry() ?:defaultCountry
    }

    override suspend fun getSavedArticle(id: String): Article? {
        return newsDao.getArticle(url = id)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article = article)
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article = article)
    }

    override fun getSavedArticleList(): Flow<List<Article>> {
       return newsDao.getArticles()
    }
}