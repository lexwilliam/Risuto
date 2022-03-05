package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.remote.anime.AnimeDetail
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    suspend fun getAnimeDetails(accessToken: String, id: Int): Flow<AnimeDetail>

}