package com.example.risuto.domain.repository

import com.example.risuto.data.local.Results
import com.example.risuto.domain.model.history.AnimeHistory
import com.example.risuto.domain.model.history.SearchHistory
import kotlinx.coroutines.flow.Flow

interface IHistoryRepository {

    fun getSearchHistory(): Flow<List<SearchHistory>>

    fun getAnimeHistory(): Flow<List<AnimeHistory>>

    suspend fun deleteSearchHistory(query: String): Flow<Int>

    suspend fun deleteAllSearch(): Flow<Int>

    suspend fun deleteAnimeByTitle(title: String): Flow<Int>

    suspend fun deleteAllAnime(): Flow<Int>

    suspend fun insertSearch(search: SearchHistory): Flow<Results>

    suspend fun insertAnime(anime: AnimeHistory): Flow<Results>
}