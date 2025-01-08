package com.shijo.newsapp.presentation.search

import androidx.paging.PagingData
import com.shijo.newsapp.data.models.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null,
    val isLoading : Boolean = false
)