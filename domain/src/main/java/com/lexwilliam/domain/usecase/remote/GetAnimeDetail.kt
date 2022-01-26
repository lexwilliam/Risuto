package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.model.remote.detail.Anime
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAnimeDetail {
    suspend fun execute(id: Int): Flow<Anime>
}

class GetAnimeDetailImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetAnimeDetail {
    override suspend fun execute(id: Int): Flow<Anime> {
        return detailRepository.anime(id)
    }
}