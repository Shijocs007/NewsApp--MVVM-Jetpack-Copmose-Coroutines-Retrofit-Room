package com.shijo.newsapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shijo.newsapp.data.models.Article
import com.shijo.newsapp.data.models.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM country")
    fun getAllCountries(): Flow<List<Country>>

    @Query("SELECT COUNT(*) FROM country")
    suspend fun getCountryCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<Country>)

    @Query("UPDATE Country SET isSelected = :isSelected WHERE code = :countryCode")
    suspend fun updateCountrySelectionStatus(countryCode: String, isSelected: Boolean)

    @Query("SELECT * FROM Country WHERE isSelected = 1 LIMIT 1")
    suspend fun getSelectedCountry(): Country?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM article")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE url=:url")
    suspend fun getArticle(url: String): Article?
}