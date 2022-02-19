package com.lexwilliam.data

import com.lexwilliam.data.model.remote.auth.AccessTokenRepo
import kotlinx.coroutines.flow.Flow

interface OAuthRemoteSource {

    suspend fun getAuthTokenLink(clientId: String, code: String, codeVerifier: String, redirectUri: String): String

    suspend fun refreshToken(clientId: String, refreshToken: String): Flow<AccessTokenRepo>

    suspend fun getAccessToken(clientId: String, code: String, codeVerifier: String): Flow<AccessTokenRepo>
}