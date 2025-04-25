package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.repository.OAuthRepository
import javax.inject.Inject


interface Logout {
    suspend fun execute()
}

class LogoutImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): Logout {
    override suspend fun execute() {
        return oAuthRepository.logout()
    }
}