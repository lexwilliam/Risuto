package com.lexwilliam.domain.repository

import kotlinx.coroutines.flow.Flow

interface OAuthRepository {

    suspend fun getAuthTokenLink(clientId: String, code: String, codeVerifier: String): String

    suspend fun setRefreshToken(clientId: String, refreshToken: String)

    suspend fun setAccessToken(clientId: String, code: String, codeVerifier: String)

    suspend fun setCodeChallenge(codeVerifier: String?)

    fun getAccessTokenFromCache(): Flow<String?>

    fun getRefreshTokenFromCache(): Flow<String?>

    fun getExpiresInFromCache(): Flow<Long?>

    suspend fun getCodeChallenge(): Flow<String?>

    suspend fun getAuthState(): Flow<String?>

    suspend fun logout()

}
