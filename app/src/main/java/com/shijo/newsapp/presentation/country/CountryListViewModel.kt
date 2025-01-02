package com.shijo.newsapp.presentation.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijo.newsapp.data.models.Country
import com.shijo.newsapp.domain.usecases.country.LoadCountryList
import com.shijo.newsapp.presentation.common.UiState
import com.shijo.newsapp.presentation.headlines.HeadLineState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
val loadCountryList: LoadCountryList
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Country>>> = _uiState

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            loadCountryList()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collectLatest {
                    _uiState.value = UiState.Success(data = it)
                }
        }
    }
}