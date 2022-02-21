package com.lexwilliam.data_remote.data

import com.lexwilliam.data.OAuthRemoteSource
import com.lexwilliam.data.model.remote.auth.AccessTokenRepo
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.OAuthMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
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
    ): Flow<AccessTokenRepo> = flow {
        val response = malService.refreshToken(
            clientId = clientId,
            refreshToken = refreshToken
        ).body()
        if(response != null) {
            emit(oAuthMapper.toRepo(response))
        } else {
            Timber.d("Response is Null")
        }
    }


    override suspend fun getAccessToken(
        clientId: String,
        code: String,
        codeVerifier: String
    ): Flow<AccessTokenRepo> = flow {
        val response = malService.getAccessToken(
            clientId = clientId,
            code = code,
            codeVerifier = codeVerifier
        ).body()
        if(response != null) {
            emit(oAuthMapper.toRepo(response))
        } else {
            Timber.d("Response is Null")
        }

    }



}