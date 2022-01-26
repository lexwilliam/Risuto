package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.model.remote.season.Season
import com.lexwilliam.domain.model.remote.top.Top
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTopAnime {
    suspend fun execute(page: Int, subType: String): Flow<Top>
}

class GetTopAnimeImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetTopAnime {
    override suspend fun execute(page: Int, subType: String): Flow<Top> {
        return animeRepository.topAnime(page, subType)
    }
}