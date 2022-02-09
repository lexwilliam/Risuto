package com.lexwilliam.data

import kotlinx.coroutines.flow.Flow
import java.util.*

interface OAuthLocalSource {
    val codeVerifier: Flow<String?>
    val state: Flow<String?>
    val accessTokenFlow: Flow<String?>
    val refreshTokenFlow: Flow<String>
    val expiresInFlow: Flow<Date>

    suspend fun setCodeVerifier(code: String)
    suspend fun setState(state: String)
    suspend fun setAccessToken(token: String)
    suspend fun setRefreshToken(token: String)
    suspend fun setExpireIn()
    suspend fun clearDataStore()
}