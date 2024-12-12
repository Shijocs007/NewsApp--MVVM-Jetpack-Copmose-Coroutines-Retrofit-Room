package com.shijo.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijo.newsapp.domain.usecases.onboarding.SaveOnboardingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val saveOnboardingStatus: SaveOnboardingStatus,
) : ViewModel() {

    fun onEvent(event: OnboardingEvent){
        when(event){
            is OnboardingEvent.SaveOnboardingCompleted ->{
                saveUserEntry()
            }
        }
    }

    private fun saveUserEntry() {
        viewModelScope.launch {
            saveOnboardingStatus()
        }
    }
}