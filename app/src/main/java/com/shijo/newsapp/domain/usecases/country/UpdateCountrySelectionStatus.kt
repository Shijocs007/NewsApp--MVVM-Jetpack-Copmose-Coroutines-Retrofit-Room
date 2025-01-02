package com.shijo.newsapp.domain.usecases.country

import com.shijo.newsapp.domain.repository.CountryListRepository

class UpdateCountrySelectionStatus(
    private val countryListRepository: CountryListRepository
) {
    suspend operator fun invoke(countryCode: String, status: Boolean) {
        countryListRepository.updateCountrySelectionStatus(
            countryCode = countryCode,
            status = status
        )
    }
}