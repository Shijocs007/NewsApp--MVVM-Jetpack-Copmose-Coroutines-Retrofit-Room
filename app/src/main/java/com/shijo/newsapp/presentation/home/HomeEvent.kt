package com.shijo.newsapp.presentation.home

import com.shijo.newsapp.presentation.navigation.Route

sealed class HomeEvent {
    data class OnBottomNavClick(val index: Int) : HomeEvent()
}