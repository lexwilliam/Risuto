package com.lexwilliam.data_local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data_local.datastore.DataStoreConstant.ACCESS_TOKEN
import com.lexwilliam.data_local.datastore.DataStoreConstant.CODE_VERIFIER
import com.lexwilliam.data_local.datastore.DataStoreConstant.EXPIRES_IN
import com.lexwilliam.data_local.datastore.DataStoreConstant.REFRESH_TOKEN
import com.lexwilliam.data_local.datastore.DataStoreConstant.STATE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import kotlin.coroutines.coroutineContext

internal val Context.dataStore by preferencesDataStore(
    name = DataStoreConstant.PREFERENCES_NAME
)

class OAuthLocalSourceImpl(
    private val dataStore: DataStore<Preferences>
): OAuthLocalSource {

    override val codeVerifier: Flow<String?> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        Timber.d("Collected access token on $coroutineContext")
        preferences[CODE_VERIFIER]
    }.flowOn(Dispatchers.IO)

    override val state: Flow<String?> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        Timber.d("Collected access token on $coroutineContext")
        preferences[STATE]
    }.flowOn(Dispatchers.IO)

    override val accessTokenFlow: Flow<String?> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        Timber.d("Collected access token on $coroutineContext")
        preferences[ACCESS_TOKEN]
    }.flowOn(Dispatchers.IO)

    override val refreshTokenFlow: Flow<String> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        preferences[REFRESH_TOKEN]?.let {
            return@map it
        } ?: run {
            return@map ""
        }
    }.flowOn(Dispatchers.IO)

    override val expiresInFlow: Flow<Long?> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        preferences[EXPIRES_IN]
    }.flowOn(Dispatchers.IO)

    override suspend fun setCodeVerifier(code: String): Unit = withContext(Dispatchers.IO) {
        dataStore.edit { preferences ->
            preferences[CODE_VERIFIER] = code
        }
    }

    override suspend fun setState(state: String) : Unit = withContext(Dispatchers.IO) {
        dataStore.edit { preferences ->
            preferences[STATE] = state
        }
    }


    override suspend fun setAccessToken(token: String): Unit = withContext(Dispatchers.IO) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = token
        }
    }

    override suspend fun setRefreshToken(token: String): Unit = withContext(Dispatchers.IO) {
        dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN] = token
        }
    }

    override suspend fun setExpireIn(time: Long): Unit = withContext(Dispatchers.IO) {
        dataStore.edit { preferences ->
            preferences[EXPIRES_IN] = System.currentTimeMillis() + time
        }
    }

    override suspend fun clearDataStore(): Unit = withContext(Dispatchers.IO) {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}