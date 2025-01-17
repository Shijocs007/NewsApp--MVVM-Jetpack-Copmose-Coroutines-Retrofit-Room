package com.shijo.newsapp.presentation.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijo.newsapp.data.models.Country
import com.shijo.newsapp.domain.usecases.country.LoadCountryList
import com.shijo.newsapp.domain.usecases.country.UpdateCountrySelectionStatus
import com.shijo.newsapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val loadCountryList: LoadCountryList,
    private val updateCountrySelectionStatus: UpdateCountrySelectionStatus
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Country>>> = _uiState

    private var currentSelectedCountry : Country? = null

    init {
        loadCountries()
    }

    fun onEvent(event: CountryListEvent) {
        when (event) {
            is CountryListEvent.OnCountrySelected -> {
                viewModelScope.launch(Dispatchers.Main.immediate) {
                    currentSelectedCountry?.let {
                        // clear the previous selected one
                        updateCountrySelectionStatus.invoke(countryCode = it.code, status = false)
                    }
                    updateCountrySelectionStatus.invoke(countryCode = event.country.code, status = true)
                }
            }
        }
    }

    private fun loadCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            loadCountryList()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collectLatest {
                    setSelectedCountry(it)
                    _uiState.value = UiState.Success(data = it)
                }
        }
    }

    private fun setSelectedCountry(countries: List<Country>) {
        currentSelectedCountry = countries.find { it.isSelected }
    }
}