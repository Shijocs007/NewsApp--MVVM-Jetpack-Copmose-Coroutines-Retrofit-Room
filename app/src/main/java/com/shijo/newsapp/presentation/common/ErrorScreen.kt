package com.shijo.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.shijo.newsapp.ui.theme.Dimes
import com.shijo.newsapp.ui.theme.NewsAppTheme

@Composable
fun ErrorScreen(message: String) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.errorContainer)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = Dimes.TextLarge
            ),
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier
                .padding(Dimes.PaddingMedium)
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ErrorScreenPreview() {
    NewsAppTheme {
        ErrorScreen(message = "Something went wrong!")
    }
}