package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.remote.auth.AccessToken
import kotlinx.coroutines.flow.Flow
import java.util.*

interface OAuthRepository {

    suspend fun getAuthTokenLink(clientId: String, code: String, codeVerifier: String): String

    suspend fun refreshToken(clientId: String, refreshToken: String): Flow<AccessToken>

    suspend fun getAccessToken(clientId: String, code: String, codeVerifier: String): Flow<AccessToken>

    suspend fun setCodeChallenge(codeVerifier: String?)

    fun getAccessTokenFromCache(): Flow<String?>

    fun getRefreshTokenFromCache(): Flow<String?>

    fun getExpiresInFromCache(): Flow<Long?>

    suspend fun getCodeChallenge(): Flow<String?>

    suspend fun getAuthState(): Flow<String?>

}
