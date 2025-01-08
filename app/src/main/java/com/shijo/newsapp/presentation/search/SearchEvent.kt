package com.shijo.newsapp.presentation.search


sealed class SearchEvent {
    data class SearchNews(val searchQuery: String) : SearchEvent()
}