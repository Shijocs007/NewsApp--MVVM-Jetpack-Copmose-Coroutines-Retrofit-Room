package com.shijo.newsapp.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shijo.newsapp.presentation.navigation.HomeNavGraph
import com.shijo.newsapp.presentation.home.components.HomeBottomNavigation
import com.shijo.newsapp.presentation.navigation.Route

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val isBottomBarEnabled = (currentDestination?.hasRoute<Route.HeadLineScreen>() == true
            || currentDestination?.hasRoute<Route.SearchScreen>() == true
            || currentDestination?.hasRoute<Route.BookmarkScreen>() == true)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarEnabled) {
                HomeBottomNavigation(
                    items = state.bottomNavItems,
                    selectedItem = state.selectedItemIndex,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HeadLineScreen
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
                        onEvent(HomeEvent.OnBottomNavClick(index = index))
                    }
                )
            }
        },
    ) {
        val paddingBottom = it.calculateBottomPadding()
        HomeNavGraph(
            modifier = Modifier.padding(bottom = paddingBottom),
            navController = navController
        )
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
