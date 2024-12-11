package com.shijo.newsapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {

    suspend fun saveOnboardingStatus()

    fun getOnboardingStatus(): Flow<Boolean>
}