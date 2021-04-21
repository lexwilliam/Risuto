package com.example.risuto.domain.repository

import com.example.risuto.domain.model.Anime
import com.example.risuto.domain.model.CharacterStaff
import com.example.risuto.domain.model.SeasonArchive
import kotlinx.coroutines.flow.Flow

interface IItemRepository {

    suspend fun getAnime(id: Int): Flow<Anime>

    suspend fun getCharacterStaff(id: Int): Flow<CharacterStaff>

    suspend fun getSeasonArchive(): Flow<SeasonArchive>
}