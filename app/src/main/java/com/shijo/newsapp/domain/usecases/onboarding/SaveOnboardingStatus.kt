package com.shijo.newsapp.domain.usecases.onboarding

import com.shijo.newsapp.domain.repository.OnboardingRepository

class SaveOnboardingStatus(
    private val onboardingRepository: OnboardingRepository
) {

    suspend operator fun invoke() {
        onboardingRepository.saveOnboardingStatus()
    }
}