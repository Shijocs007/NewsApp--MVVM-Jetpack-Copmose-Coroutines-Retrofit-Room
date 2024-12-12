package com.shijo.newsapp.presentation.onboarding

sealed class OnboardingEvent {
    data object SaveOnboardingCompleted : OnboardingEvent()
}