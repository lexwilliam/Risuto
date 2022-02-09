package com.lexwilliam.data.repository

import com.lexwilliam.data.ClientId
import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data.OAuthRemoteSource
import com.lexwilliam.domain.repository.OAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class OAuthRepositoryImpl @Inject constructor(
    private val oAuthRemoteSource: OAuthRemoteSource,
    private val oAuthLocalSource: OAuthLocalSource
): OAuthRepository {
    override suspend fun getAuthTokenLink(code: String, codeVerifier: String): String =
        oAuthRemoteSource.getAuthTokenLink(
            clientId = ClientId.CLIENT_ID,
            code = code,
            codeVerifier = codeVerifier,
            redirectUri = ClientId.REDIRECT_URI
        )

    override suspend fun getAccessToken(code: String, codeVerifier: String){
        withContext(Dispatchers.IO) {
            val response = oAuthRemoteSource.getAccessToken(
                clientId = ClientId.CLIENT_ID,
                code = code,
                codeVerifier = codeVerifier
            )
            if(response.accessToken != "") {
                Timber.d(response.accessToken)
                oAuthLocalSource.setAccessToken(response.accessToken)
                oAuthLocalSource.setExpireIn()
                oAuthLocalSource.setRefreshToken(response.refreshToken)
            }
        }
    }

    override suspend fun setCodeChallengeAndState(codeVerifier: String?) {
        if(codeVerifier != null) {
            oAuthLocalSource.setCodeVerifier(codeVerifier)
        }
    }

    override suspend fun getCodeChallenge(): Flow<String?> {
        return oAuthLocalSource.codeVerifier
    }

    override suspend fun getAuthState(): Flow<String?>  {
        return oAuthLocalSource.state
    }
}