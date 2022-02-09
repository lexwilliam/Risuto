package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.repository.OAuthRepository
import javax.inject.Inject

interface GetAccessToken {
    suspend fun execute(code: String, codeVerifier: String)
}

class GetAccessTokenImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): GetAccessToken {
    override suspend fun execute(code: String, codeVerifier: String) {
        return oAuthRepository.getAccessToken(code, codeVerifier)
    }
}