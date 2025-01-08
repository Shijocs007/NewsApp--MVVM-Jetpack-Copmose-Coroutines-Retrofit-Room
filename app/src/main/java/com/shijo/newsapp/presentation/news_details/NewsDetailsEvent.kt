package com.shijo.newsapp.presentation.news_details

import com.shijo.newsapp.data.models.Article

sealed class NewsDetailsEvent {
    data class OnBookmarkClicked(val article: Article) : NewsDetailsEvent()
    data class CheckBookmarkedStatus(val article: Article) : NewsDetailsEvent()
}