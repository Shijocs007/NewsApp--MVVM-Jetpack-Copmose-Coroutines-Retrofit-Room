package com.shijo.newsapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shijo.newsapp.domain.usecases.news.SearchNews
import com.shijo.newsapp.utils.Constants.SEARCH_TIMEOUT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchNews: SearchNews
) : ViewModel() {

    private var _uiState = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _uiState

    private var query = MutableStateFlow("")

    init {
        createSearchFlow()
    }


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _uiState.value = _uiState.value.copy(searchQuery = event.searchQuery)
                query.value = event.searchQuery
            }
        }
    }

    private fun createSearchFlow() {
        viewModelScope.launch {
            query.debounce(SEARCH_TIMEOUT)
                .filter {
                    _uiState.value = _uiState.value.copy(searchQuery = it)
                    if (it.isNotEmpty() && it.length >= 2) {
                        return@filter true
                    } else {
                        return@filter false
                    }
                }
                .distinctUntilChanged()
                .map {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                    return@map searchNews(it)
                        .catch { e ->
                            _uiState.value = _uiState.value.copy(articles = null, isLoading = false)
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    _uiState.value = _uiState.value.copy(articles = it, isLoading = false)
                }
        }
    }

}