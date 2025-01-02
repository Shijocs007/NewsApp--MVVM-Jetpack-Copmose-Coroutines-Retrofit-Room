package com.shijo.newsapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.shijo.newsapp.data.datastore.PreferenceKeys
import com.shijo.newsapp.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : OnboardingRepository {
    override suspend fun saveOnboardingStatus() {
        dataStore.edit { settings ->
            settings[PreferenceKeys.KEY_ONBOARDING_STATUS] = true
        }
    }

    override fun getOnboardingStatus(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[PreferenceKeys.KEY_ONBOARDING_STATUS] ?: false
        }
    }
}