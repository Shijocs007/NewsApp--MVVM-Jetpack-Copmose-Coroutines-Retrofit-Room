package com.shijo.newsapp.data.repository

import android.app.Application
import androidx.datastore.preferences.core.edit
import com.shijo.newsapp.data.datastore.PreferenceKeys
import com.shijo.newsapp.data.datastore.dataStore
import com.shijo.newsapp.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnboardingRepositoryImpl(
    private val application: Application
) : OnboardingRepository {
    override suspend fun saveOnboardingStatus() {
        application.dataStore.edit { settings ->
            settings[PreferenceKeys.ONBOARDING_STATUS] = true
        }
    }

    override fun getOnboardingStatus(): Flow<Boolean> {
        return application.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.ONBOARDING_STATUS] ?: false
        }
    }
}