package com.shijo.newsapp.domain.usecases.onboarding

import com.shijo.newsapp.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow

class GetOnboardingStatus(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return onboardingRepository.getOnboardingStatus()
    }
}