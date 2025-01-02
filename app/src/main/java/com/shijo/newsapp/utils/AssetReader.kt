package com.shijo.newsapp.utils

import android.content.Context

class AssetReader(private val context: Context) {
    fun readFileFromAssets(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}