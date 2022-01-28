package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.model.remote.season.Season
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCurrentSeasonAnime {
    suspend fun execute(): Flow<Season>
}

class GetCurrentSeasonAnimeImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetCurrentSeasonAnime {
    override suspend fun execute(): Flow<Season> {
        return animeRepository.currentSeasonAnime()
    }
}