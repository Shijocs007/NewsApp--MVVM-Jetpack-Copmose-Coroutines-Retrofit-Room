package com.shijo.newsapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijo.newsapp.domain.usecases.onboarding.GetOnboardingStatus
import com.shijo.newsapp.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getOnboardingStatus: GetOnboardingStatus
): ViewModel() {
    private val _showSplashScreen = MutableStateFlow(true)
    val showSplashScreen: StateFlow<Boolean> get() = _showSplashScreen

    private val _startDestination = MutableStateFlow<Route>(Route.OnBoardingScreen)
    val startDestination: StateFlow<Route> get() = _startDestination

    init {
        checkOnboardingStatus()
    }

    private fun checkOnboardingStatus() {
        getOnboardingStatus().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                _startDestination.value = Route.HomeScreen
            }else{
                _startDestination.value = Route.OnBoardingScreen
            }
            _showSplashScreen.value = false
        }.launchIn(viewModelScope)
    }
}