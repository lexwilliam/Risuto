package com.lexwilliam.domain.usecase.remote.detail

import com.lexwilliam.domain.model.remote.detail.Reviews
import com.lexwilliam.domain.model.remote.detail.Stats
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetStats {
    suspend fun execute(id: Int): Flow<Stats>
}

class GetStatsImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetStats {
    override suspend fun execute(id: Int): Flow<Stats> {
        return detailRepository.stats(id)
    }
}