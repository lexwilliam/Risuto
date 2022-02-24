package com.lexwilliam.domain.usecase.remote.anime

import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAnimeById {
    suspend fun execute(id: Int): Flow<Anime.Data>
}

class GetAnimeByIdImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetAnimeById {
    override suspend fun execute(id: Int): Flow<Anime.Data> {
        return animeRepository.getAnimeById(id)
    }
}