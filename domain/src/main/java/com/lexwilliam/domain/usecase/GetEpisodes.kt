package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.detail.CharacterStaff
import com.lexwilliam.domain.model.remote.detail.Episodes
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetEpisodes {
    suspend fun execute(id: Int): Flow<Episodes>
}

class GetEpisodesImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetEpisodes {
    override suspend fun execute(id: Int): Flow<Episodes> {
        return detailRepository.episodes(id)
    }
}