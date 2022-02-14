package com.lexwilliam.data.repository

import com.lexwilliam.data.BuildConfig
import com.lexwilliam.data.DataConstant
import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data.OAuthRemoteSource
import com.lexwilliam.domain.model.remote.auth.AccessToken
import com.lexwilliam.domain.repository.OAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class OAuthRepositoryImpl @Inject constructor(
    private val oAuthRemoteSource: OAuthRemoteSource,
    private val oAuthLocalSource: OAuthLocalSource
): OAuthRepository {
    override suspend fun getAuthTokenLink(code: String, codeVerifier: String): String =
        oAuthRemoteSource.getAuthTokenLink(
            clientId = BuildConfig.CLIENT_ID,
            code = code,
            codeVerifier = codeVerifier,
            redirectUri = DataConstant.REDIRECT_URI
        )

    override suspend fun refreshToken(): Int {
        var result: Int = -1
        oAuthLocalSource.refreshTokenFlow.collect {
            val response = oAuthRemoteSource.refreshToken(
                clientId = BuildConfig.CLIENT_ID,
                refreshToken = it
            )
            if(response.accessToken != "") {
                Timber.d(response.accessToken)
                oAuthLocalSource.setAccessToken(response.accessToken)
                oAuthLocalSource.setExpireIn(response.expiresIn)
                oAuthLocalSource.setRefreshToken(response.refreshToken)
                result = 0
            }
        }
        return result
    }

    override suspend fun getAccessToken(code: String, codeVerifier: String): Int {
        val response = oAuthRemoteSource.getAccessToken(
            clientId = BuildConfig.CLIENT_ID,
            code = code,
            codeVerifier = codeVerifier
        )
        if(response.accessToken != "") {
            Timber.d(response.accessToken)
            oAuthLocalSource.setAccessToken(response.accessToken)
            oAuthLocalSource.setExpireIn(response.expiresIn)
            oAuthLocalSource.setRefreshToken(response.refreshToken)
            return 0
        } else {
            return -1
        }
    }

    override suspend fun setCodeChallengeAndState(codeVerifier: String?) {
        if(codeVerifier != null) {
            oAuthLocalSource.setCodeVerifier(codeVerifier)
        }
    }

    override suspend fun getTokenInfo(): AccessToken {
        var accessToken: String? = null
        var refreshToken: String? = null
        var expiredIn: Int? = null
        oAuthLocalSource.accessTokenFlow.collect { accessToken = it }
        oAuthLocalSource.refreshTokenFlow.collect { refreshToken = it }
        oAuthLocalSource.expiresInFlow.collect { expiredIn = it }
        return AccessToken(accessToken, expiredIn, refreshToken, "")
    }

    override suspend fun getCodeChallenge(): Flow<String?> {
        return oAuthLocalSource.codeVerifier
    }

    override suspend fun getAuthState(): Flow<String?>  {
        return oAuthLocalSource.state
    }
}