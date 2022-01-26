package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.local.AnimeHistory
import com.lexwilliam.domain.model.local.SearchHistory
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getSearchHistory(): Flow<List<SearchHistory>>

    fun getAnimeHistory(): Flow<List<AnimeHistory>>

    suspend fun deleteSearchHistory(query: String): Int

    suspend fun deleteAllSearch(): Int

    suspend fun deleteAnimeByTitle(title: String): Int

    suspend fun deleteAllAnime(): Int

    suspend fun insertSearch(search: SearchHistory): Long

    suspend fun insertAnime(anime: AnimeHistory): Long
}