package com.shijo.newsapp.data.api

import com.shijo.newsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        // Add common query parameters
        val url = originalUrl.newBuilder()
            .addQueryParameter("apiKey", BuildConfig.API_KEY)
            .build()

        // Add common headers
        val requestBuilder = originalRequest.newBuilder()
            .url(url)
            .addHeader("User-Agent", "com.shijo.newsapp/1.0")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
