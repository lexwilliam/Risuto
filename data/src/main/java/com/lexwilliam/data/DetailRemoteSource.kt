package com.lexwilliam.data

import com.lexwilliam.data.model.remote.anime.AnimeCharactersRepo
import com.lexwilliam.data.model.remote.anime.AnimeDetailRepo
import com.lexwilliam.data.model.remote.anime.AnimeStaffRepo
import com.lexwilliam.data.model.remote.anime.AnimeVideosRepo
import kotlinx.coroutines.flow.Flow

interface DetailRemoteSource {

    suspend fun getAnimeDetails(authHeader: String, id: Int): Flow<AnimeDetailRepo>

    suspend fun getAnimeCharacters(id: Int): Flow<AnimeCharactersRepo>

    suspend fun getAnimeVideos(id: Int): Flow<AnimeVideosRepo>

    suspend fun getAnimeStaff(id: Int): Flow<AnimeStaffRepo>
}