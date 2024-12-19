package com.shijo.newsapp.data.models
import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("urlToImage")
    val imageUrl: String? = "",
    @SerializedName("source")
    val source: Source?,
    @SerializedName("publishedAt")
    val publishedAt: String? = ""
)