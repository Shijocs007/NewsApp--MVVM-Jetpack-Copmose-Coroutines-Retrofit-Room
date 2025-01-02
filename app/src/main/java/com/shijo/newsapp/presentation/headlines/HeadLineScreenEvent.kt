package com.shijo.newsapp.presentation.headlines

sealed class HeadLineScreenEvent {

    data object RefreshScreen : HeadLineScreenEvent()
}