package com.shijo.newsapp.presentation.headlines

import com.shijo.newsapp.data.models.Article

sealed class HeadLineScreenEvent {

    data object RefreshScreen : HeadLineScreenEvent() }