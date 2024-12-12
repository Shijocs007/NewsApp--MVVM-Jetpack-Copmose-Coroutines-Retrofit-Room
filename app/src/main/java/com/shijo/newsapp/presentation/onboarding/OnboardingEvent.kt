package com.shijo.newsapp.presentation.onboarding

sealed class OnboardingEvent {

    data class OnboardingSwiped(val selectedPage: Int) : OnboardingEvent()

    data object SaveOnboardingCompleted : OnboardingEvent()
}