package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.repository.OAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAuthState {
    suspend fun execute(): Flow<String?>
}

class GetAuthStateImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): GetAuthState {
    override suspend fun execute(): Flow<String?> {
        return oAuthRepository.getAuthState()
    }
}