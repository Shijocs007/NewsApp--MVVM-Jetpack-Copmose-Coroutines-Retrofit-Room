package com.shijo.newsapp.presentation.country

import com.shijo.newsapp.data.models.Country

sealed class CountryListEvent {
    data class OnCountrySelected(val country: Country) : CountryListEvent()
}