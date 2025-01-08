package com.shijo.newsapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijo.newsapp.domain.usecases.news.SearchNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchNews: SearchNews
) : ViewModel() {

    private var _uiState = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _uiState


    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.SearchNews -> {
                performSearchRequest(searchQuery = event.searchQuery)
            }
        }
    }

    private fun performSearchRequest(searchQuery : String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(searchQuery = searchQuery, isLoading = true)
            }
            searchNews(searchQuery = searchQuery)
                .flowOn(Dispatchers.IO)
                .catch {
                    _uiState.value = _uiState.value.copy(
                        searchQuery = searchQuery,
                        isLoading = false,
                        articles = null
                    )
                }
                .collectLatest {
                    _uiState.value = _uiState.value.copy(
                        searchQuery = searchQuery,
                        isLoading = false,
                        articles = it
                    )
                }
        }
    }

}