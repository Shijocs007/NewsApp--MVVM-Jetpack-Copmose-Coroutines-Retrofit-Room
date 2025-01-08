package com.shijo.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.shijo.newsapp.data.models.toArticle
import com.shijo.newsapp.data.models.toArticleString
import com.shijo.newsapp.presentation.common.ErrorScreen
import com.shijo.newsapp.presentation.country.CountryListScreen
import com.shijo.newsapp.presentation.country.CountryListViewModel
import com.shijo.newsapp.presentation.headlines.HeadlineScreen
import com.shijo.newsapp.presentation.headlines.HeadlineViewModel
import com.shijo.newsapp.presentation.news_details.DetailsScreen
import com.shijo.newsapp.presentation.news_details.NewsDetailsViewModel
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
                isRefreshScreen = isRefreshScreen ?: false,
                navigateToDetails = { article ->
                    navController.navigate(
                        Route.NewsDetailsScreen(
                            article = article.toArticleString()
                        )
                    )
                }
            )
            entry.savedStateHandle[Constants.REFRESH_SCREEN] = null

        }
        composable<Route.SearchScreen> {
            ErrorScreen(message = "Search screen will be implemented.")
        }
        composable<Route.BookmarkScreen> {
            ErrorScreen(message = "Bookmark screen will be implemented")
        }
        composable<Route.NewsDetailsScreen> { entry ->
            val route : Route.NewsDetailsScreen = entry.toRoute()
            val viewModel: NewsDetailsViewModel = hiltViewModel()
            DetailsScreen(
                article = route.article.toArticle(),
                uiEvent = viewModel.uiEvents.collectAsState().value,
                navigateUp = {
                    navController.navigateUp()
                },
                onEvent = { event ->
                    viewModel.onEvent(event = event)
                }
            )
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