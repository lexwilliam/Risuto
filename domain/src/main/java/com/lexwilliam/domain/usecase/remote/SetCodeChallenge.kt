package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.repository.OAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SetCodeChallenge {
    suspend fun execute(codeVerifier: String?)
}

class SetCodeChallengeImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): SetCodeChallenge {
    override suspend fun execute(codeVerifier: String?) {
        return oAuthRepository.setCodeChallengeAndState(codeVerifier)
    }
}