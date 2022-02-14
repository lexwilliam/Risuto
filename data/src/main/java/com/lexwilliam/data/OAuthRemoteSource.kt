package com.lexwilliam.data

import com.lexwilliam.data.model.remote.auth.AccessTokenRepo

interface OAuthRemoteSource {

    suspend fun getAuthTokenLink(clientId: String, code: String, codeVerifier: String, redirectUri: String): String

    suspend fun refreshToken(clientId: String, refreshToken: String): AccessTokenRepo

    suspend fun getAccessToken(clientId: String, code: String, codeVerifier: String): AccessTokenRepo
}