package com.shijo.newsapp.presentation.headlines

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.data.models.Country
import com.shijo.newsapp.data.models.Source
import com.shijo.newsapp.presentation.common.ErrorScreen
import com.shijo.newsapp.presentation.common.NewsItem
import com.shijo.newsapp.presentation.common.NewsListShimmer
import com.shijo.newsapp.presentation.common.UiState
import com.shijo.newsapp.presentation.headlines.components.HeadLineTopBar
import com.shijo.newsapp.ui.theme.Dimes
import com.shijo.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

@Composable
fun HeadlineScreen(
    uiState: UiState<HeadLineState>,
    onCountryClicked: () -> Unit = {},
    isRefreshScreen : Boolean,
    onEvent : (HeadLineScreenEvent) -> Unit = {},
    navigateToDetails: (Article) -> Unit
) {
   LaunchedEffect(isRefreshScreen) {
       if (isRefreshScreen) {
           onEvent(HeadLineScreenEvent.RefreshScreen)
       }
   }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (uiState is UiState.Success) {
                HeadLineTopBar(
                    country = uiState.data.selectedCountry,
                    onCountryClicked = {
                        onCountryClicked()
                    }
                )
            }
        }
    ) {
        val padding = it.calculateBottomPadding()
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
                    items(uiState.data.articles.size) { index ->
                        NewsItem(
                            article = uiState.data.articles[index],
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



@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewNewsScreen() {
    NewsAppTheme {
        HeadlineScreen(
            navigateToDetails = {},
            isRefreshScreen = false,
            uiState = UiState.Success(
                HeadLineState(
                    articles = listOf(
                        Article(
                            title = "Breaking News: Kotlin Rules",
                            description = "Kotlin has become the most loved language for Android developers worldwide.",
                            url = "https://example.com/kotlin",
                            imageUrl = "https://example.com/kotlin_image.jpg",
                            source = Source(name = "Tech News"),
                            publishedAt = "2024-12-19"
                        ),
                        Article(
                            title = "Compose UI Advances",
                            description = "Jetpack Compose is transforming the way Android UI is built, enabling faster development.",
                            url = "https://example.com/compose",
                            imageUrl = "https://example.com/compose_image.jpg",
                            source = Source(name = "Compose Weekly"),
                            publishedAt = "2024-12-18"
                        )
                    ),
                    selectedCountry = Country(
                        name = "USA",
                        code = "us",
                        flag = "🇺🇸"
                    )
                )
            )
        )
    }
}



