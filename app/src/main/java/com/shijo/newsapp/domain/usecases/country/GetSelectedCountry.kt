package com.shijo.newsapp.domain.usecases.country

import com.shijo.newsapp.data.models.Country
import com.shijo.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSelectedCountry (private val newsRepository: NewsRepository) {
    operator fun invoke() : Country {
        return newsRepository.getSelectedCountry()
    }
}