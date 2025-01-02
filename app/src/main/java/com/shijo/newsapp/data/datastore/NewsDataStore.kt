package com.shijo.newsapp.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.shijo.newsapp.utils.Constants


object PreferenceKeys {
    val KEY_ONBOARDING_STATUS = booleanPreferencesKey(Constants.ONBOARDING_SCREEN_STATUS)
    val KEY_SELECTED_COUNTRY = booleanPreferencesKey(Constants.SELECTED_COUNTRY)
}