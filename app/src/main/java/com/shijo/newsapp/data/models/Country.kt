package com.shijo.newsapp.data.models

data class Country(
    val name: String,
    val code: String,
    val flag: String
)

val defaultCountry = Country(
    name = "USA",
    code = "us",
    flag = "🇺🇸"
)
