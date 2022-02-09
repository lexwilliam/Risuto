package com.lexwilliam.domain.repository

import kotlinx.coroutines.flow.Flow
import java.util.*

interface OAuthRepository {

    suspend fun getAuthTokenLink(code: String, codeVerifier: String): String

    suspend fun getAccessToken(code: String, codeVerifier: String)

    suspend fun setCodeChallengeAndState(codeVerifier: String?)

    suspend fun getCodeChallenge(): Flow<String?>

    suspend fun getAuthState(): Flow<String?>

}
