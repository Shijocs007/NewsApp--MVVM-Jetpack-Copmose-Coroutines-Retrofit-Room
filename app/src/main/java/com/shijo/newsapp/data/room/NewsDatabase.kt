package com.shijo.newsapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shijo.newsapp.data.models.Country

@Database(entities = [Country::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract val newsDao : NewsDao
}