package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.repository.OAuthRepository
import javax.inject.Inject

interface RefreshToken {
    suspend fun execute(clientId: String, refreshToken: String): Int
}

class RefreshTokenImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): RefreshToken {
    override suspend fun execute(clientId: String, refreshToken: String): Int {
        return oAuthRepository.refreshToken(clientId, refreshToken)
    }
}