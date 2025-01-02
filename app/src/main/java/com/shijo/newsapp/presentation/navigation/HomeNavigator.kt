package com.shijo.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shijo.newsapp.presentation.common.ErrorScreen
import com.shijo.newsapp.presentation.country.CountryListScreen
import com.shijo.newsapp.presentation.country.CountryListViewModel
import com.shijo.newsapp.presentation.headlines.HeadlineScreen
import com.shijo.newsapp.presentation.headlines.HeadlineViewModel
import com.shijo.newsapp.utils.Constants

@Composable
fun HomeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.HeadLineScreen,
        modifier = modifier
    ) {
        composable<Route.HeadLineScreen> { entry ->
            val isRefreshScreen = entry.savedStateHandle.get<Boolean>(Constants.REFRESH_SCREEN)
            val viewModel: HeadlineViewModel = hiltViewModel()
            HeadlineScreen(
                uiState = viewModel.uiState.collectAsState().value,
                onCountryClicked = {
                    navController.navigate(Route.CountryListScreen)
                },
                onEvent = { event ->
                    viewModel.onEvent(event = event)
                },
                isRefreshScreen = isRefreshScreen ?: false
            )
        }
        composable<Route.SearchScreen> {
            ErrorScreen(message = "Search screen will be implemented.")
        }
        composable<Route.BookmarkScreen> {
            ErrorScreen(message = "Bookmark screen will be implemented")
        }
        composable<Route.CountryListScreen> {
            val viewModel: CountryListViewModel = hiltViewModel()
            CountryListScreen(
                uiState = viewModel.uiState.collectAsState().value,
                onBackPressed = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set(Constants.REFRESH_SCREEN, true)
                    navController.popBackStack()
                },
                onEvent = { event ->
                    viewModel.onEvent(event = event)
                }
            )
        }
    }
}