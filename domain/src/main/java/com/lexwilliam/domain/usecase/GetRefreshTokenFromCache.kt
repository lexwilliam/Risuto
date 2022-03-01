package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.repository.OAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetRefreshTokenFromCache {
    suspend fun execute(): Flow<String?>
}

class GetRefreshTokenFromCacheImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): GetRefreshTokenFromCache {
    override suspend fun execute(): Flow<String?> {
        return oAuthRepository.getRefreshTokenFromCache()
    }
}