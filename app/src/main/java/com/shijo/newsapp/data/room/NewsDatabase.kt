package com.shijo.newsapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.data.models.Country

@Database(entities = [Country::class, Article::class], version = 1)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract val newsDao : NewsDao
}