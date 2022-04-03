package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.local.AnimeHistory
import com.lexwilliam.domain.model.remote.anime.AnimeVideos
import com.lexwilliam.domain.repository.DetailRepository
import com.lexwilliam.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAnimeVideos {
    suspend fun execute(id: Int): Flow<AnimeVideos>
}

class GetAnimeVideosImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetAnimeVideos {
    override suspend fun execute(id: Int): Flow<AnimeVideos> {
        return detailRepository.getAnimeVideos(id)
    }
}