package com.lexwilliam.domain.usecase.remote.detail

import com.lexwilliam.domain.model.remote.detail.MoreInfo
import com.lexwilliam.domain.model.remote.detail.News
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetNews {
    suspend fun execute(id: Int): Flow<News>
}

class GetNewsImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetNews {
    override suspend fun execute(id: Int): Flow<News> {
        return detailRepository.news(id)
    }
}