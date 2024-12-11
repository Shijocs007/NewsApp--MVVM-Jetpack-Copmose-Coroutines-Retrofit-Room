package com.shijo.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.shijo.newsapp.domain.usecases.onboarding.SaveOnboardingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val saveBooleanUseCase: SaveOnboardingStatus,
) : ViewModel() {

    init {
        println("jhsdksb")
    }
}