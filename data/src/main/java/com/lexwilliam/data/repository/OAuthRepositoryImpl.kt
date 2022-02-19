package com.lexwilliam.data.repository

import com.lexwilliam.data.constant.DataConstant
import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data.OAuthRemoteSource
import com.lexwilliam.data.mapper.OAuthMapper
import com.lexwilliam.domain.model.remote.auth.AccessToken
import com.lexwilliam.domain.repository.OAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class OAuthRepositoryImpl @Inject constructor(
    private val oAuthRemoteSource: OAuthRemoteSource,
    private val oAuthLocalSource: OAuthLocalSource,
    private val oAuthMapper: OAuthMapper
): OAuthRepository {
    override suspend fun getAuthTokenLink(clientId: String, code: String, codeVerifier: String): String =
        oAuthRemoteSource.getAuthTokenLink(
            clientId = clientId,
            code = code,
            codeVerifier = codeVerifier,
            redirectUri = DataConstant.REDIRECT_URI
        )

    override suspend fun refreshToken(clientId: String, refreshToken: String): Flow<AccessToken> =
        oAuthRemoteSource.refreshToken(
            clientId = clientId,
            refreshToken = refreshToken
        ).map { oAuthMapper.toDomain(it) }

    override suspend fun getAccessToken(clientId: String, code: String, codeVerifier: String): Flow<AccessToken> =
        oAuthRemoteSource.getAccessToken(
            clientId = clientId,
            code = code,
            codeVerifier = codeVerifier
        ).map { oAuthMapper.toDomain(it) }
//        return if(response.accessToken != "") {
//            Timber.d(response.accessToken)
//            oAuthLocalSource.setAccessToken(response.accessToken)
//            oAuthLocalSource.setExpireIn(response.expiresIn.toLong())
//            oAuthLocalSource.setRefreshToken(response.refreshToken)
//            0
//        } else {
//            -1
//        }

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
}