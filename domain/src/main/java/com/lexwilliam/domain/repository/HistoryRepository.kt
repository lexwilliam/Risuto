package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.local.AnimeHistory
import com.lexwilliam.domain.model.local.SearchHistory
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getSearchHistory(): Flow<List<SearchHistory>>

    fun getAnimeHistory(): Flow<List<AnimeHistory>>

    suspend fun deleteSearchHistory(query: String): Flow<Int>

    suspend fun deleteAllSearch(): Flow<Int>

    suspend fun deleteAnimeByTitle(title: String): Flow<Int>

    suspend fun deleteAllAnime(): Flow<Int>

    suspend fun insertSearch(search: SearchHistory): Flow<Long>

    suspend fun insertAnime(anime: AnimeHistory): Flow<Long>
}