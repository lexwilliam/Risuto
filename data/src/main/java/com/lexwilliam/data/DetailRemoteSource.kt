package com.lexwilliam.data

import com.lexwilliam.data.model.remote.anime.AnimeCharactersRepo
import com.lexwilliam.data.model.remote.anime.AnimeDetailRepo
import kotlinx.coroutines.flow.Flow

interface DetailRemoteSource {

    suspend fun getAnimeDetails(authHeader: String, id: Int): Flow<AnimeDetailRepo>

    suspend fun getAnimeCharacters(id: Int): Flow<AnimeCharactersRepo>
}