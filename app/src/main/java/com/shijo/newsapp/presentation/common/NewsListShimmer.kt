package com.shijo.newsapp.presentation.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shijo.newsapp.ui.theme.Dimes


@Composable
fun ShimmerEffect(modifier: Modifier = Modifier) {
    val shimmer = remember { Animatable(0f) }

    // Animate the shimmer effect
    LaunchedEffect(shimmer) {
        shimmer.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1200,
                    easing = LinearEasing
                )
            )
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .alpha(shimmer.value)
    ) {
        // Shimmer background (gradient effect)
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        Color.Gray.copy(alpha = 0.3f),
                        Color.Gray.copy(alpha = 0.1f)
                    )
                )
            ))
    }
}

@Composable
fun NewsListShimmer(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(Dimes.PaddingSmall)) {
        NewsItemShimmer()
        Spacer(modifier = Modifier.height(Dimes.SpacerSmall))
        NewsItemShimmer()
        Spacer(modifier = Modifier.height(Dimes.SpacerSmall))
        NewsItemShimmer()
        Spacer(modifier = Modifier.height(Dimes.SpacerSmall))
        NewsItemShimmer()
        Spacer(modifier = Modifier.height(Dimes.SpacerSmall))
    }
}

@Composable
fun NewsItemShimmer() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimes.PaddingMedium),
        shape = RoundedCornerShape(Dimes.ElevationLarge),
        elevation = CardDefaults.cardElevation(Dimes.ElevationMedium)
    ) {
        Column(modifier = Modifier.padding(Dimes.PaddingMedium)) {
            // Shimmer Effect for Image
            ShimmerEffect(modifier = Modifier
                .height(Dimes.ArticleImageHeight)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium))

            Spacer(modifier = Modifier.height(Dimes.SpacerSmall))

            // Shimmer Effect for Text (Title)
            ShimmerEffect(modifier = Modifier
                .fillMaxWidth()
                .height(Dimes.PaddingMedium))

            Spacer(modifier = Modifier.height(Dimes.SpacerExtraSmall))

            // Shimmer Effect for Text (Description)
            ShimmerEffect(modifier = Modifier
                .fillMaxWidth()
                .height(Dimes.PaddingMedium))

            Spacer(modifier = Modifier.height(Dimes.SpacerSmall))

            // Shimmer Effect for Row with Text
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Shimmer Effect for Source
                ShimmerEffect(modifier = Modifier
                    .height(12.dp)
                    .width(120.dp))

                // Shimmer Effect for Date
                ShimmerEffect(modifier = Modifier
                    .height(12.dp)
                    .width(80.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsItemShimmer() {
    NewsListShimmer()
}
