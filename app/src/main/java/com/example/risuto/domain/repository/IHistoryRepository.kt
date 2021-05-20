package com.example.risuto.domain.repository

import com.example.risuto.data.local.Results
import com.example.risuto.domain.model.AnimeHistory
import com.example.risuto.domain.model.SearchHistory
import kotlinx.coroutines.flow.Flow

interface IHistoryRepository {

    fun getSearchHistory(): Flow<List<SearchHistory>>

    fun getAnimeHistory(): Flow<List<AnimeHistory>>

    fun deleteSearchByTitle(query: String): Flow<Int>

    fun deleteAllSearch(): Flow<Int>

    fun deleteAnimeByTitle(title: String): Flow<Int>

    fun deleteAllAnime(): Flow<Int>

    fun insertSearch(search: SearchHistory): Flow<Results>

    fun insertAnime(anime: AnimeHistory): Flow<Results>
}