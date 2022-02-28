package com.lexwilliam.domain.usecase.remote.detail

import com.lexwilliam.data.model.remote.detail.Pictures
import com.lexwilliam.domain.model.remote.detail.Recommendations
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetRecommendations {
    suspend fun execute(id: Int): Flow<Recommendations>
}

class GetRecommendationsImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetRecommendations {
    override suspend fun execute(id: Int): Flow<Recommendations> {
        return detailRepository.recommendations(id)
    }
}