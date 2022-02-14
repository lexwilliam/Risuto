package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.repository.OAuthRepository
import javax.inject.Inject

interface RefreshToken {
    suspend fun execute(): Int
}

class RefreshTokenImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): RefreshToken {
    override suspend fun execute(): Int {
        return oAuthRepository.refreshToken()
    }
}