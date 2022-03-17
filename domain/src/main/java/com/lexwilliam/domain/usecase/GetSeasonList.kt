package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.model.remote.anime.SeasonList
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSeasonList {
    suspend fun execute(): Flow<SeasonList>
}

class GetSeasonListImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetSeasonList {
    override suspend fun execute(): Flow<SeasonList> {
        return animeRepository.getSeasonList()
    }
}