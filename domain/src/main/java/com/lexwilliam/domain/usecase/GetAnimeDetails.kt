package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.model.remote.anime.AnimeDetail
import com.lexwilliam.domain.repository.AnimeRepository
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAnimeDetails {
    suspend fun execute(id: Int): Flow<AnimeDetail>
}

class GetAnimeDetailsImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetAnimeDetails {
    override suspend fun execute(id: Int): Flow<AnimeDetail> {
        return detailRepository.getAnimeDetails(id)
    }
}