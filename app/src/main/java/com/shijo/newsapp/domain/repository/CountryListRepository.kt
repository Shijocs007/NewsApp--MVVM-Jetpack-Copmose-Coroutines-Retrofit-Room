package com.shijo.newsapp.domain.repository

import com.shijo.newsapp.data.models.Country
import kotlinx.coroutines.flow.Flow

interface CountryListRepository {
    suspend fun loadCountryList() : Flow<List<Country>>
    suspend fun updateCountrySelectionStatus(countryCode : String, status : Boolean)
}