package com.shijo.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijo.newsapp.domain.usecases.onboarding.SaveOnboardingStatus
import com.shijo.newsapp.presentation.onboarding.components.pages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val saveOnboardingStatus: SaveOnboardingStatus,
) : ViewModel() {

    private val _onboardingState = MutableStateFlow(
        OnboardingState.OnboardingCurrentPageState(
            page = pages[0],
            button = getButtonTexts(0)
        )
    )

    val onboardingState : StateFlow<OnboardingState> = _onboardingState


    fun onEvent(event: OnboardingEvent){
        when(event){
            is OnboardingEvent.SaveOnboardingCompleted ->{
                saveUserEntry()
            }
            is OnboardingEvent.OnboardingSwiped -> {
                _onboardingState.value = OnboardingState.OnboardingCurrentPageState(
                    page = pages[event.selectedPage],
                    button = getButtonTexts(event.selectedPage)
                )
            }
        }
    }

    private fun saveUserEntry() {
        viewModelScope.launch {
            saveOnboardingStatus()
        }
    }


    private fun getButtonTexts(page: Int): List<String> {
        return when (page) {
            0 -> listOf("", "Next")
            1 -> listOf("Back", "Next")
            2 -> listOf("Back", "Get Started")
            else -> listOf("", "")
        }

    }

}