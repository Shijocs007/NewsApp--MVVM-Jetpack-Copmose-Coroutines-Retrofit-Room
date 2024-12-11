package com.shijo.newsapp.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.shijo.newsapp.utils.Constants


object PreferenceKeys {
    val ONBOARDING_STATUS = booleanPreferencesKey(Constants.ONBOARDING_SCREEN_STATUS)
}