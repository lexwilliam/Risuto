package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.remote.anime.AnimeCharacters
import com.lexwilliam.domain.model.remote.anime.AnimeDetail
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    suspend fun getAnimeDetails(id: Int): Flow<AnimeDetail>

    suspend fun getAnimeCharacters(id: Int): Flow<AnimeCharacters>
}