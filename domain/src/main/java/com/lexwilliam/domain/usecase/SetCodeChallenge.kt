package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.repository.OAuthRepository
import javax.inject.Inject

interface SetCodeChallenge {
    suspend fun execute(codeVerifier: String?)
}

class SetCodeChallengeImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): SetCodeChallenge {
    override suspend fun execute(codeVerifier: String?) {
        return oAuthRepository.setCodeChallenge(codeVerifier)
    }
}