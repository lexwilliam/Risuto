package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSchedules {
    suspend fun execute(dayOfWeek: String): Flow<Anime>
}

class GetSchedulesImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetSchedules {
    override suspend fun execute(dayOfWeek: String): Flow<Anime> {
        return animeRepository.getSchedules(dayOfWeek)
    }
}