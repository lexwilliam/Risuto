package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.anime.AnimeCharacters
import com.lexwilliam.domain.model.remote.anime.AnimeDetail
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAnimeCharacters {
    suspend fun execute(id: Int): Flow<AnimeCharacters>
}

class GetAnimeCharactersImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetAnimeCharacters {
    override suspend fun execute(id: Int): Flow<AnimeCharacters> {
        return detailRepository.getAnimeCharacters(id)
    }
}