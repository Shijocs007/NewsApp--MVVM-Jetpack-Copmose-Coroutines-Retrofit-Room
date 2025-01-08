package com.shijo.newsapp.presentation.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.domain.usecases.news.GetSavedArticleList
import com.shijo.newsapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewmodel @Inject constructor(
    private val getSavedArticleList: GetSavedArticleList
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        fetchSavedArticleList()
    }

    private fun fetchSavedArticleList() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            getSavedArticleList()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collectLatest {
                    if(it.isEmpty()) {
                        _uiState.value = UiState.Error("No Items Bookmarked")
                    } else {
                        _uiState.value = UiState.Success(data = it)
                    }
                }
        }

    }
}