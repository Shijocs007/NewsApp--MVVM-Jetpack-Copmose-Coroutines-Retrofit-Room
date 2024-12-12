package com.shijo.newsapp.presentation.onboarding

import com.shijo.newsapp.presentation.onboarding.components.Page

sealed class OnboardingState {

    data class OnboardingCurrentPageState(
        val page: Page,
        val button: List<String>
    ) : OnboardingState()

    data object NavigateToHome : OnboardingState()
}