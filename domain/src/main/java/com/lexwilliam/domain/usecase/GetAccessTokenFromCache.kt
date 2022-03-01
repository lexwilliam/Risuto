package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.repository.OAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAccessTokenFromCache {
    fun execute(): Flow<String?>
}

class GetAccessTokenFromCacheImpl @Inject constructor(
    private val oAuthRepository: OAuthRepository
): GetAccessTokenFromCache {
    override fun execute(): Flow<String?> {
        return oAuthRepository.getAccessTokenFromCache()
    }
}