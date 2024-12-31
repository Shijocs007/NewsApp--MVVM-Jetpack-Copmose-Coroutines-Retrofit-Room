package com.shijo.newsapp.presentation.home

import com.shijo.newsapp.utils.BottomNavigationItem


data class HomeState(
    val isBottomBarEnabled: Boolean = true,
    val selectedItemIndex: Int = 0,
    val bottomNavItems : List<BottomNavigationItem>
)
