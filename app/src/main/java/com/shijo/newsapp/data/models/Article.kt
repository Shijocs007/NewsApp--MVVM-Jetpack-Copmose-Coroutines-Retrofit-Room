package com.shijo.newsapp.data.models
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


@Entity
data class Article(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @PrimaryKey
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val imageUrl: String? = "",
    @SerializedName("source")
    val source: Source?,
    @SerializedName("publishedAt")
    val publishedAt: String? = ""
)


fun Article.toArticleString(): String {
    return Gson().toJson(this)
}

fun String.toArticle(): Article {
    return Gson().fromJson(this, Article::class.java)
}
