package com.shijo.newsapp.presentation.headlines

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.shijo.newsapp.ui.theme.Dimes.ExtraSmallPadding2
import com.shijo.newsapp.ui.theme.NewsAppTheme

@Composable
fun TopHeadlineScreen(uiState: UiState<List<Article>>) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
                HeadLineTopBar(
                    country = Country(
                        name = "USA",
                        code = "us",
                        flag = "🇺🇸"
                    )
                )
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
                            start = ExtraSmallPadding2,
                            end = ExtraSmallPadding2,
                            bottom = ExtraSmallPadding2
                        ),
                    contentPadding = PaddingValues(ExtraSmallPadding2),
                    verticalArrangement = Arrangement.spacedBy(ExtraSmallPadding2)
                ) {
                    items(uiState.data.size) { index ->
                        NewsItem(article = uiState.data[index])
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
        TopHeadlineScreen(
            uiState = UiState.Success(listOf(
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
            ))
        )
    }
}



