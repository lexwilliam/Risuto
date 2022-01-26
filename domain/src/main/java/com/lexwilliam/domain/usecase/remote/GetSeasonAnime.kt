package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.model.remote.search.Search
import com.lexwilliam.domain.model.remote.season.Season
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSeasonAnime {
    suspend fun execute(year: Int, season: String): Flow<Season>
}

class GetSeasonAnimeImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetSeasonAnime {
    override suspend fun execute(year: Int, season: String): Flow<Season> {
        return animeRepository.seasonAnime(year, season)
    }
}