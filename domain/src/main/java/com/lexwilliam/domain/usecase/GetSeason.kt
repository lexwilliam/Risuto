package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSeason {
    suspend fun execute(year: Int, season: String): Flow<Anime>
}

class GetSeasonImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetSeason {
    override suspend fun execute(year: Int, season: String): Flow<Anime> {
        return animeRepository.getSeason(year, season)
    }
}