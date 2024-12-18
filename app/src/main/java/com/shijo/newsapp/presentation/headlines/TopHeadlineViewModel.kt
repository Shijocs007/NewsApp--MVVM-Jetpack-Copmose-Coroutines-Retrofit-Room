package com.shijo.newsapp.presentation.headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijo.newsapp.domain.usecases.news.GetTopHeadLines
import com.shijo.newsapp.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.amitshekhar.newsapp.data.model.Article
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val getTopHeadLines: GetTopHeadLines
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        fetchTopHeadLines()
    }

    private fun fetchTopHeadLines() {
        viewModelScope.launch {
            getTopHeadLines("us")
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collectLatest {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}