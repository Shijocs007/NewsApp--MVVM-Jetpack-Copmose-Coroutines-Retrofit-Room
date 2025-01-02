package com.shijo.newsapp.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shijo.newsapp.data.models.Country
import com.shijo.newsapp.data.room.NewsDao
import com.shijo.newsapp.domain.repository.CountryListRepository
import com.shijo.newsapp.utils.AssetReader
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryListRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val assetReader: AssetReader,
    private val gson: Gson
) : CountryListRepository {
    override suspend fun loadCountryList(): Flow<List<Country>> {
        if(newsDao.getCountryCount() == 0) {
            val countryString = assetReader.readFileFromAssets("country_list.json")
            val countryList = parseCountryList(countryString)
            newsDao.insertCountries(countries = countryList)
        }
        return newsDao.getAllCountries()
    }

    private fun parseCountryList(countryString: String) : List<Country> {
        try {
            val type = object : TypeToken<List<Country>>() {}.type
            val countryList = gson.fromJson<List<Country>>(countryString, type)
            return countryList
        } catch (e : Exception) {
        }
        return emptyList()
    }
}