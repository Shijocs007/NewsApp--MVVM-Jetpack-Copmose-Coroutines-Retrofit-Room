package com.shijo.newsapp.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.shijo.newsapp.utils.Constants

private val dataStore = preferencesDataStore(name = Constants.NEWS_APP_DATASTORE_MANAGER)

val Context.dataStore: DataStore<Preferences> by dataStore

object PreferenceKeys {
    val ONBOARDING_STATUS = booleanPreferencesKey(Constants.ONBOARDING_SCREEN_STATUS)
}