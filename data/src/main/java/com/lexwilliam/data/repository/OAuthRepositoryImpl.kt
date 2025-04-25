package com.lexwilliam.data.repository

import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data.OAuthRemoteSource
import com.lexwilliam.data.constant.DataConstant
import com.lexwilliam.domain.repository.OAuthRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class OAuthRepositoryImpl @Inject constructor(
    private val oAuthRemoteSource: OAuthRemoteSource,
    private val oAuthLocalSource: OAuthLocalSource
): OAuthRepository {
    override suspend fun getAuthTokenLink(clientId: String, code: String, codeVerifier: String): String =
        oAuthRemoteSource.getAuthTokenLink(
            clientId = clientId,
            code = code,
            codeVerifier = codeVerifier,
            redirectUri = DataConstant.REDIRECT_URI
        )

    override suspend fun setRefreshToken(clientId: String, refreshToken: String) {
        val response = oAuthRemoteSource.refreshToken(
            clientId = clientId,
            refreshToken = refreshToken
        )
        response.collect { token ->
            Timber.d(token.accessToken)
            oAuthLocalSource.setAccessToken(token.accessToken)
            oAuthLocalSource.setExpireIn(token.expiresIn.toLong())
            oAuthLocalSource.setRefreshToken(token.refreshToken)
        }
    }

    override suspend fun setAccessToken(clientId: String, code: String, codeVerifier: String) {
        val response = oAuthRemoteSource.getAccessToken(
            clientId = clientId,
            code = code,
            codeVerifier = codeVerifier
        )
        response.collect { token ->
            Timber.d(token.accessToken)
            oAuthLocalSource.setAccessToken(token.accessToken)
            oAuthLocalSource.setExpireIn(token.expiresIn.toLong())
            oAuthLocalSource.setRefreshToken(token.refreshToken)
        }
    }

    override suspend fun setCodeChallenge(codeVerifier: String?) {
        if(codeVerifier != null) {
            oAuthLocalSource.setCodeVerifier(codeVerifier)
        }
    }

    override fun getAccessTokenFromCache(): Flow<String?> {
        return oAuthLocalSource.accessTokenFlow
    }

    override fun getRefreshTokenFromCache(): Flow<String?> {
        return oAuthLocalSource.refreshTokenFlow
    }

    override fun getExpiresInFromCache(): Flow<Long?> {
        return oAuthLocalSource.expiresInFlow
    }

    override suspend fun getCodeChallenge(): Flow<String?> {
        return oAuthLocalSource.codeVerifier
    }

    override suspend fun getAuthState(): Flow<String?>  {
        return oAuthLocalSource.state
    }

    override suspend fun logout() {
        return oAuthLocalSource.clearDataStore()
    }
}