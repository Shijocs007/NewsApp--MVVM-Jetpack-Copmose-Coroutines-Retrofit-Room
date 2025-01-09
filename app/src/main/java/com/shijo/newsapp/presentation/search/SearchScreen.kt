package com.shijo.newsapp.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.presentation.common.ErrorScreen
import com.shijo.newsapp.presentation.common.NewsItem
import com.shijo.newsapp.presentation.search.components.SearchBar
import com.shijo.newsapp.ui.theme.Dimes


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    navigateToDetails: (Article) -> Unit,
    onEvent : (SearchEvent) -> Unit
) {

    val articles = state.articles?.collectAsLazyPagingItems()
    Column(
        modifier = modifier
            .padding(
                top = Dimes.PaddingExtraSmall,
            )
            .statusBarsPadding()
    ) {
        SearchBar(
            isLoading = state.isLoading,
            text = state.searchQuery,
            onValueChange = {
                onEvent(SearchEvent.UpdateSearchQuery(searchQuery = it))
            },
            onSearch = {

            }
        )
        Spacer(modifier = Modifier.height(Dimes.PaddingExtraSmall))

        if (articles == null || articles.itemCount == 0) {
            ErrorScreen(message =if(state.isLoading) "Searching..." else "No item found, Type a new keyword")
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(
                        top = Dimes.PaddingExtraSmall,
                        start = Dimes.PaddingExtraSmall,
                        end = Dimes.PaddingExtraSmall,
                        bottom = Dimes.PaddingExtraSmall
                    ),
                contentPadding = PaddingValues(Dimes.PaddingExtraSmall),
                verticalArrangement = Arrangement.spacedBy(Dimes.PaddingExtraSmall)
            ) {
                items(articles.itemCount) { index ->
                    articles[index]?.let {
                        NewsItem(
                            article = it,
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