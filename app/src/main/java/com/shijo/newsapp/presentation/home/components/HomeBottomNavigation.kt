package com.shijo.newsapp.presentation.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shijo.newsapp.ui.theme.Dimes
import com.shijo.newsapp.ui.theme.NewsAppTheme
import com.shijo.newsapp.utils.BottomNavigationItem


@Composable
fun HomeBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = Dimes.ElevationLarge
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}



@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview() {
    NewsAppTheme(dynamicColor = false) {
        HomeBottomNavigation(items = listOf(
            BottomNavigationItem(label = "Home", icon = Icons.Default.Home),
            BottomNavigationItem(label = "Search", icon = Icons.Default.Search),
            BottomNavigationItem(label = "Bookmarks", icon = Icons.Rounded.Star),
        ), selectedItem = 0, onItemClick = {})
    }
}
