package com.lexwilliam.domain.usecase.remote.detail

import com.lexwilliam.domain.model.remote.detail.AnimeDetail
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAnimeDetail {
    suspend fun execute(id: Int): Flow<AnimeDetail>
}

class GetAnimeDetailImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetAnimeDetail {
    override suspend fun execute(id: Int): Flow<AnimeDetail> {
        return detailRepository.anime(id)
    }
}