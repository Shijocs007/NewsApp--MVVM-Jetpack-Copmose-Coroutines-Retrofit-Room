package com.shijo.newsapp.presentation.navigation

import com.shijo.newsapp.data.models.Article
import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object OnBoardingScreen : Route()

    @Serializable
    data object HomeScreen : Route()

    @Serializable
    data object HeadLineScreen : Route()

    @Serializable
    data object SearchScreen : Route()

    @Serializable
    data object BookmarkScreen : Route()

    @Serializable
    data object CountryListScreen : Route()

    @Serializable
    data class NewsDetailsScreen(val article: String) : Route()

}