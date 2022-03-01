package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTopAnime {
    suspend fun execute(): Flow<Anime>
}

class GetTopAnimeImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetTopAnime {
    override suspend fun execute(): Flow<Anime> {
        return animeRepository.getTopAnime()
    }
}