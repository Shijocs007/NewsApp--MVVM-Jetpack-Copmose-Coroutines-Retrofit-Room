package com.shijo.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.data.models.Source
import com.shijo.newsapp.ui.theme.Dimes
import com.shijo.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsItem(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click here, e.g., navigate to details */ },
        shape = RoundedCornerShape(Dimes.ElevationLarge),
        elevation = CardDefaults.cardElevation(Dimes.ElevationMedium)
    ) {
        Column(modifier = Modifier.padding(Dimes.PaddingMedium)) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimes.ArticleImageHeight)
                    .clip(MaterialTheme.shapes.medium),
                model = ImageRequest.Builder(LocalContext.current).data(article.imageUrl).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(Dimes.SpacerSmall))
            Text(
                text = article.title ?:"",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(Dimes.SpacerSmall))
            Text(
                text = article.description?:"",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(Dimes.SpacerSmall))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = article.source?.name?:"",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = article.publishedAt?:"",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewNewsCard() {
    NewsAppTheme {
        NewsItem(
            article = Article(
                title = "Breaking News: Kotlin Rules",
                description = "Kotlin has become the most loved language for Android developers worldwide.",
                url = "https://example.com/kotlin",
                imageUrl = "https://example.com/kotlin_image.jpg",
                source = Source(name = "Tech News"),
                publishedAt = "2024-12-19"
            )
        )
    }
}
