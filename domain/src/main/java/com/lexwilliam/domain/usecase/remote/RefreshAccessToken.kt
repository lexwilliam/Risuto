package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.repository.OAuthRepository
import javax.inject.Inject

interface RefreshAccessToken {
    suspend fun execute(clientId: String, refreshToken: String): Int
}

class RefreshAccessTokenImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): RefreshAccessToken {
    override suspend fun execute(clientId: String, refreshToken: String): Int {
        return oAuthRepository.refreshToken(clientId, refreshToken)
    }
}