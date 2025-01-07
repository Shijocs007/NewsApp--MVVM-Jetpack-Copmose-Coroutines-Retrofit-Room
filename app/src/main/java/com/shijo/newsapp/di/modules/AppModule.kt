package com.shijo.newsapp.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.shijo.newsapp.data.api.NewsApiService
import com.shijo.newsapp.data.room.NewsDao
import com.shijo.newsapp.data.room.NewsDatabase
import com.shijo.newsapp.data.room.NewsTypeConvertor
import com.shijo.newsapp.di.BaseUrl
import com.shijo.newsapp.utils.AssetReader
import com.shijo.newsapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NewsApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NewsApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "db_news"
        ).addTypeConverter(NewsTypeConvertor()).build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

    @Provides
    @Singleton
    fun provideAssetReader(@ApplicationContext context: Context): AssetReader = AssetReader(context)

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}