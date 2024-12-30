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
    Column(modifier = Modifier.padding(8.dp)) {
        NewsItemShimmer()
        Spacer(modifier = Modifier.height(8.dp))
        NewsItemShimmer()
        Spacer(modifier = Modifier.height(8.dp))
        NewsItemShimmer()
        Spacer(modifier = Modifier.height(8.dp))
        NewsItemShimmer()
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun NewsItemShimmer() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Shimmer Effect for Image
            ShimmerEffect(modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium))

            Spacer(modifier = Modifier.height(8.dp))

            // Shimmer Effect for Text (Title)
            ShimmerEffect(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp))

            Spacer(modifier = Modifier.height(4.dp))

            // Shimmer Effect for Text (Description)
            ShimmerEffect(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))

            Spacer(modifier = Modifier.height(8.dp))

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
