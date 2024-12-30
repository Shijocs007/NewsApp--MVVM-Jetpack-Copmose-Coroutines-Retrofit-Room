package com.shijo.newsapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object OnBoardingScreen : Route()

    @Serializable
    data object HomeScreen : Route()

    @Serializable
    data object TopHeadLineScreen : Route()

    @Serializable
    data object SearchScreen : Route()

    @Serializable
    data object BookmarkScreen : Route()

}