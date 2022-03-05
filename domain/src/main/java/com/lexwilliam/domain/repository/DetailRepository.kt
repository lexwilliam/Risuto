package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.remote.anime.AnimeDetail
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    suspend fun getAnimeDetails(id: Int): Flow<AnimeDetail>

}