package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.remote.auth.AccessToken
import kotlinx.coroutines.flow.Flow
import java.util.*

interface OAuthRepository {

    suspend fun getAuthTokenLink(code: String, codeVerifier: String): String

    suspend fun refreshToken(): Int

    suspend fun getAccessToken(code: String, codeVerifier: String): Int

    suspend fun setCodeChallengeAndState(codeVerifier: String?)

    suspend fun getTokenInfo(): AccessToken

    suspend fun getCodeChallenge(): Flow<String?>

    suspend fun getAuthState(): Flow<String?>

}
