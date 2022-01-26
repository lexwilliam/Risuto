package com.lexwilliam.data

import com.lexwilliam.data.model.local.AnimeHistoryRepo
import com.lexwilliam.data.model.local.SearchHistoryRepo
import kotlinx.coroutines.flow.Flow

interface HistoryLocalSource {

    fun getSearchHistory(): Flow<List<SearchHistoryRepo>>

    fun getAnimeHistory(): Flow<List<AnimeHistoryRepo>>

    suspend fun deleteSearchHistory(query: String): Int

    suspend fun deleteAllSearch(): Int

    suspend fun deleteAnimeByTitle(title: String): Int

    suspend fun deleteAllAnime(): Int

    suspend fun insertSearch(search: SearchHistoryRepo): Long

    suspend fun insertAnime(anime: AnimeHistoryRepo): Long

}