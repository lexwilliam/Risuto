package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.detail.Recommendations
import com.lexwilliam.domain.model.remote.detail.Reviews
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetReviews {
    suspend fun execute(id: Int): Flow<Reviews>
}

class GetReviewsImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetReviews {
    override suspend fun execute(id: Int): Flow<Reviews> {
        return detailRepository.reviews(id)
    }
}