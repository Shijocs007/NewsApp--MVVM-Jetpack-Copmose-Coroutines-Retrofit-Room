package com.shijo.newsapp.presentation.headlines

import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.data.models.Country

data class HeadLineState(
    val articles : List<Article>,
    val selectedCountry: Country
)
