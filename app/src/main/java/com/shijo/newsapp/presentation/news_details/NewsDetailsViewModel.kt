package com.shijo.newsapp.presentation.news_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.domain.usecases.news.DeleteArticle
import com.shijo.newsapp.domain.usecases.news.GetSavedArticle
import com.shijo.newsapp.domain.usecases.news.UpsertArticle
import com.shijo.newsapp.utils.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val getSavedArticleUseCase: GetSavedArticle,
    private val deleteArticleUseCase: DeleteArticle,
    private val upsertArticleUseCase: UpsertArticle
) : ViewModel() {

    private val _uiEvents = MutableStateFlow<UIComponent?>(null)
    val uiEvents: StateFlow<UIComponent?> = _uiEvents

    private val _uiState = MutableStateFlow(NewsDetailsState())
    val uiState: StateFlow<NewsDetailsState> = _uiState


    fun onEvent(event: NewsDetailsEvent) {
        when(event) {
            is NewsDetailsEvent.OnBookmarkClicked -> {
                viewModelScope.launch {
                    val article = getSavedArticleUseCase(id = event.article.url)
                    if (article == null){
                        upsertArticle(article = event.article)
                    }else{
                        deleteArticle(article = event.article)
                    }
                }
            }

            is NewsDetailsEvent.CheckBookmarkedStatus -> {
                viewModelScope.launch {
                    val article = getSavedArticleUseCase(id = event.article.url)
                    _uiState.value = NewsDetailsState(isBookeMarked = article != null)
                }
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        deleteArticleUseCase(article = article)
        _uiEvents.value = UIComponent.Toast("Article deleted")
        _uiState.value = NewsDetailsState(isBookeMarked = false)
    }

    private suspend fun upsertArticle(article: Article) {
        upsertArticleUseCase(article = article)
        _uiEvents.value = UIComponent.Toast("Article Inserted")
        _uiState.value = NewsDetailsState(isBookeMarked = true)
    }

}