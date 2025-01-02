package com.shijo.newsapp.domain.usecases.country

import com.shijo.newsapp.data.models.Country
import com.shijo.newsapp.domain.repository.CountryListRepository
import kotlinx.coroutines.flow.Flow

class LoadCountryList(val countryListRepository: CountryListRepository) {

    suspend operator fun invoke() : Flow<List<Country>> {
        return countryListRepository.loadCountryList()
    }
}