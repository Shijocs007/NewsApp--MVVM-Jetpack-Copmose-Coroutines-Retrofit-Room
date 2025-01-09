package com.shijo.newsapp.presentation.search


sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
}