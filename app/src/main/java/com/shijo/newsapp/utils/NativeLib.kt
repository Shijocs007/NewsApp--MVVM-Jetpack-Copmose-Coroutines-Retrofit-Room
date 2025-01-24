package com.shijo.newsapp.utils

object NativeLib {
    init {
        System.loadLibrary("native-lib")
    }

    @JvmStatic
    external fun getApiKey(): String
}