package com.shijo.newsapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Country(
    val name: String,
    @PrimaryKey val code: String,
    val flag: String
)

val defaultCountry = Country(
    name = "USA",
    code = "us",
    flag = "🇺🇸"
)
