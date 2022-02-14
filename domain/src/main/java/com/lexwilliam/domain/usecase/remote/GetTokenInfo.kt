package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.model.remote.auth.AccessToken
import com.lexwilliam.domain.repository.OAuthRepository
import javax.inject.Inject

interface GetTokenInfo {
    suspend fun execute(): AccessToken
}

class GetTokenInfoImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): GetTokenInfo {
    override suspend fun execute(): AccessToken {
        return oAuthRepository.getTokenInfo()
    }
}