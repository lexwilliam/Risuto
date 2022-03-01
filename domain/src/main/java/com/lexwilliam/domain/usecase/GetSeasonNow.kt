package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSeasonNow {
    suspend fun execute(): Flow<Anime>
}

class GetSeasonNowImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetSeasonNow {
    override suspend fun execute(): Flow<Anime> {
        return animeRepository.getSeasonNow()
    }
}