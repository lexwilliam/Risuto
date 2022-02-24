package com.lexwilliam.domain.usecase.remote.anime

import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTopAnimeV4 {
    suspend fun execute(): Flow<Anime>
}

class GetTopAnimeV4Impl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetTopAnimeV4 {
    override suspend fun execute(): Flow<Anime> {
        return animeRepository.getTopAnime()
    }
}