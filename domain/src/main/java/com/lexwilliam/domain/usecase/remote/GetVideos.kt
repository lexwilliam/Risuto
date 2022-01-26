package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.model.remote.detail.Stats
import com.lexwilliam.domain.model.remote.detail.Videos
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetVideos {
    suspend fun execute(id: Int): Flow<Videos>
}

class GetVideosImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetVideos {
    override suspend fun execute(id: Int): Flow<Videos> {
        return detailRepository.videos(id)
    }
}