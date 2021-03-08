package com.example.risuto.domain.repository

import com.example.risuto.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface IItemRepository {

    suspend fun getAnime(id: Int): Flow<Anime>

}