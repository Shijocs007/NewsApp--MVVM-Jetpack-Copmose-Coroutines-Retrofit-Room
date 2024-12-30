package com.shijo.newsapp.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shijo.newsapp.presentation.common.ErrorScreen
import com.shijo.newsapp.presentation.headlines.TopHeadlineScreen
import com.shijo.newsapp.presentation.headlines.TopHeadlineViewModel
import com.shijo.newsapp.presentation.navigation.BottomNavigationItem
import com.shijo.newsapp.presentation.navigation.NewsBottomNavigation
import com.shijo.newsapp.presentation.navigation.Route

@Composable
fun HomeScreen() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(label = "Home", icon = Icons.Default.Home),
            BottomNavigationItem(label = "Search", icon = Icons.Default.Search),
            BottomNavigationItem(label = "Bookmarks", icon = Icons.Rounded.Star)
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }


    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = true

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.TopHeadLineScreen
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.BookmarkScreen
                        )
                    }
                }
            )
        }
    }) {

        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.TopHeadLineScreen,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable<Route.TopHeadLineScreen> { backStackEntry ->
                val viewModel: TopHeadlineViewModel = hiltViewModel()
                TopHeadlineScreen(uiState = viewModel.uiState.collectAsState().value)
            }
            composable<Route.SearchScreen> { backStackEntry ->
              ErrorScreen(message = "Search screen will be implemented.")
            }
            composable<Route.BookmarkScreen> { backStackEntry ->
                ErrorScreen(message = "Bookmark screen will be implemented")
            }
        }

    }

}

fun navigateToTab(navController: NavHostController, route: Route) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
