package com.lexwilliam.data_remote.data

import com.lexwilliam.data.OAuthRemoteSource
import com.lexwilliam.data.model.remote.auth.AccessTokenRepo
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.OAuthMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OAuthRemoteSourceImpl @Inject constructor(
    private val malService: MyAnimeListService,
    private val oAuthMapper: OAuthMapper
): OAuthRemoteSource {
    override suspend fun getAuthTokenLink(
        clientId: String,
        code: String,
        codeVerifier: String,
        redirectUri: String
    ): String =
        MyAnimeListService.getAuthTokenLink(clientId, code, codeVerifier, redirectUri)

    override suspend fun refreshToken(
        clientId: String,
        refreshToken: String
    ): AccessTokenRepo =
        oAuthMapper.toRepo(
            malService.refreshTokenAsync(
                clientId = clientId,
                refreshToken = refreshToken
            ).body()!!
        )

    override suspend fun getAccessToken(clientId: String, code: String, codeVerifier: String): AccessTokenRepo =
        oAuthMapper.toRepo(
            malService.getAccessTokenAsync(
                clientId = clientId,
                code = code,
                codeVerifier = codeVerifier
            ).body()!!
        )


}