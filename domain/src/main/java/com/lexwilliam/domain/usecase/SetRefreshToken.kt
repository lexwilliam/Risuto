package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.repository.OAuthRepository
import javax.inject.Inject

interface SetRefreshToken {
    suspend fun execute(clientId: String, refreshToken: String)
}

class SetRefreshTokenImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): SetRefreshToken {
    override suspend fun execute(clientId: String, refreshToken: String) {
        return oAuthRepository.setRefreshToken(clientId, refreshToken)
    }
}