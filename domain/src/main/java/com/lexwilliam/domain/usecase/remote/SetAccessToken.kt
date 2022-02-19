package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.repository.OAuthRepository
import javax.inject.Inject

interface SetAccessToken {
    suspend fun execute(clientId: String, code: String, codeVerifier: String)
}

class GetAccessTokenImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): SetAccessToken {
    override suspend fun execute(clientId: String, code: String, codeVerifier: String) {
        return oAuthRepository.setAccessToken(clientId, code, codeVerifier)
    }
}