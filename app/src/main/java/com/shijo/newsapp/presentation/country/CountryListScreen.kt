package com.shijo.newsapp.presentation.country

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.shijo.newsapp.R
import com.shijo.newsapp.data.models.Country
import com.shijo.newsapp.data.models.defaultCountry
import com.shijo.newsapp.presentation.common.ErrorScreen
import com.shijo.newsapp.presentation.common.LoadingScreen
import com.shijo.newsapp.presentation.common.UiState
import com.shijo.newsapp.presentation.country.components.CountryItem
import com.shijo.newsapp.ui.theme.Dimes
import com.shijo.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(
    modifier: Modifier = Modifier,
    uiState: UiState<List<Country>>
    ) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = { Text(stringResource(R.string.country_list)) },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
            )
        }
    ) {
        when (uiState) {
            is UiState.Error -> {
                ErrorScreen(message = uiState.message)
            }

            UiState.Loading -> {
                LoadingScreen()
            }
            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = it.calculateTopPadding(),
                            start = Dimes.PaddingExtraSmall,
                            end = Dimes.PaddingExtraSmall
                        )
                ) {
                    items(uiState.data.size) { index ->
                        CountryItem(country = uiState.data[index])
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCountry() {
    NewsAppTheme {
        CountryListScreen(uiState = UiState.Success(
            data = listOf(defaultCountry, defaultCountry, defaultCountry)
        ))
    }
}