package com.shijo.newsapp.presentation.search


sealed class SearchEvent {
    data class SearchNews(val searchQuery: String) : SearchEvent()
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
}