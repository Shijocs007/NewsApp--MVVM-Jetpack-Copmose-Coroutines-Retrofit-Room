package com.shijo.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shijo.newsapp.presentation.home.HomeScreen
import com.shijo.newsapp.presentation.home.HomeViewModel
import com.shijo.newsapp.presentation.onboarding.OnBoardingScreen
import com.shijo.newsapp.presentation.onboarding.OnboardingViewModel


@Composable
fun NavGraph(startDestination : Route) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable<Route.OnBoardingScreen> {
            val onboardingViewmodel : OnboardingViewModel = hiltViewModel()
            OnBoardingScreen(
                state = onboardingViewmodel.onboardingState.collectAsState().value,
                onEvent = { event ->
                    onboardingViewmodel.onEvent(event = event)
                }
            )
        }
        composable<Route.HomeScreen> {
            val homeViewModel : HomeViewModel = hiltViewModel()
            HomeScreen(
                state = homeViewModel.state.collectAsState().value,
                onEvent = {event ->
                    homeViewModel.onEvent(event = event)
                })
        }

    }
}