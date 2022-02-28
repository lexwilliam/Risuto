package com.lexwilliam.domain.usecase.remote.auth

import com.lexwilliam.domain.repository.OAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCodeChallenge {
    suspend fun execute(): Flow<String?>
}

class GetCodeChallengeImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): GetCodeChallenge {
    override suspend fun execute(): Flow<String?> {
        return oAuthRepository.getCodeChallenge()
    }
}