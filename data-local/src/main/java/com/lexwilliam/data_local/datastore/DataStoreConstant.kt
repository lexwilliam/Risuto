package com.lexwilliam.data_local.datastore

import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreConstant {
    val CODE_VERIFIER = stringPreferencesKey("code_verifier")
    val STATE = stringPreferencesKey("state")
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    val EXPIRES_IN = longPreferencesKey("expires_in")

    const val PREFERENCES_NAME = "oauth_preferences"
}