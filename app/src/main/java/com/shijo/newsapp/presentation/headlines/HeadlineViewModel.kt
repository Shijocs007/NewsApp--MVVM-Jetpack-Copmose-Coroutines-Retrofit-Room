package com.shijo.newsapp.presentation.headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijo.newsapp.domain.usecases.country.GetSelectedCountry
import com.shijo.newsapp.domain.usecases.news.GetTopHeadLines
import com.shijo.newsapp.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlineViewModel @Inject constructor(
    private val getTopHeadLines: GetTopHeadLines,
    private val getSelectedCountry: GetSelectedCountry
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<HeadLineState>>(UiState.Loading)

    val uiState: StateFlow<UiState<HeadLineState>> = _uiState

    init {
        fetchTopHeadLines()
    }

    private fun fetchTopHeadLines() {
        viewModelScope.launch {
            val selectedCountry = getSelectedCountry()
            getTopHeadLines(selectedCountry.code)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collectLatest {
                    _uiState.value = UiState.Success(
                        data = HeadLineState(
                            articles = it,
                            selectedCountry = selectedCountry
                        )
                    )
                }
        }
    }
}