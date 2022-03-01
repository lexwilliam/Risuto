package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.repository.OAuthRepository
import javax.inject.Inject

interface GetAuthTokenLink {
    suspend fun execute(clientId: String, code: String, codeVerifier: String): String
}

class GetAuthTokenLinkImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): GetAuthTokenLink {
    override suspend fun execute(clientId: String, code: String, codeVerifier: String): String {
        return oAuthRepository.getAuthTokenLink(clientId, code, codeVerifier)
    }
}