package com.shijo.newsapp.presentation.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.presentation.common.ErrorScreen
import com.shijo.newsapp.presentation.common.NewsItem
import com.shijo.newsapp.presentation.common.NewsListShimmer
import com.shijo.newsapp.ui.theme.Dimes
import com.shijo.newsapp.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    uiState: UiState<List<Article>>,
    navigateToDetails: (Article) -> Unit
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = {
                    Text(
                        text = "Bookmarks",
                        style = TextStyle(
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            fontSize = Dimes.TextLarge
                        )
                    )
                })
        }
    ) {
        val padding = it.calculateTopPadding()
        when (uiState) {
            is UiState.Error -> {
                ErrorScreen(message = uiState.message)
            }
            UiState.Loading -> {
                NewsListShimmer()
            }

            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(
                            top = padding,
                            start = Dimes.PaddingExtraSmall,
                            end = Dimes.PaddingExtraSmall,
                            bottom = Dimes.PaddingExtraSmall
                        ),
                    contentPadding = PaddingValues(Dimes.PaddingExtraSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimes.PaddingExtraSmall)
                ) {
                    items(uiState.data.size) { index ->
                        NewsItem(
                            article = uiState.data[index],
                            onArticleClicked = { article ->
                                navigateToDetails(article)
                            }
                        )
                    }
                }
            }
        }
    }

}