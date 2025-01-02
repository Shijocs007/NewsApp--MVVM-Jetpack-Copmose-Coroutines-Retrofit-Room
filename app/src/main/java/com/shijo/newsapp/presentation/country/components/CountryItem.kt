package com.shijo.newsapp.presentation.country.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.data.models.Country
import com.shijo.newsapp.data.models.Source
import com.shijo.newsapp.data.models.defaultCountry
import com.shijo.newsapp.presentation.common.NewsItem
import com.shijo.newsapp.ui.theme.Dimes
import com.shijo.newsapp.ui.theme.NewsAppTheme

@Composable
fun CountryItem(
    modifier: Modifier = Modifier,
    country: Country,
    onCountrySelected : (Country) -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimes.PaddingSmall)
            .clickable {
                onCountrySelected(country)
            },
        elevation = CardDefaults.cardElevation(Dimes.ElevationMedium),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(Dimes.PaddingMedium)
        ) {
            Text(text = country.flag, style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.width(Dimes.SpacerMedium))

            // Country name and code
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = country.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = country.code, style = MaterialTheme.typography.bodySmall)
            }
            if(country.isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Checkmark",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(Dimes.IconLarge) // Adjust size as needed
                )
            }

        }
    }

}


@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCountry() {
    NewsAppTheme {
      CountryItem(country = defaultCountry)
    }
}