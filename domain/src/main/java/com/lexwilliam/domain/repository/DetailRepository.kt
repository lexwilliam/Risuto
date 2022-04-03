package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.remote.anime.AnimeCharacters
import com.lexwilliam.domain.model.remote.anime.AnimeDetail
import com.lexwilliam.domain.model.remote.anime.AnimeVideos
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    suspend fun getAnimeDetails(id: Int): Flow<AnimeDetail>

    suspend fun getAnimeCharacters(id: Int): Flow<AnimeCharacters>

    suspend fun getAnimeVideos(id: Int): Flow<AnimeVideos>
}