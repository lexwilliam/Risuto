package com.lexwilliam.domain.usecase.local.auth

import com.lexwilliam.domain.repository.OAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetExpiresInFromCache {
    suspend fun execute(): Flow<Long?>
}

class GetExpiresInFromCacheImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): GetExpiresInFromCache {
    override suspend fun execute(): Flow<Long?> {
        return oAuthRepository.getExpiresInFromCache()
    }
}