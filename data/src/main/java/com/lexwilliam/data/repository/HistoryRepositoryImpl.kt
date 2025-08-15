package com.lexwilliam.data.repository

import com.lexwilliam.data.HistoryLocalSource
import com.lexwilliam.data.mapper.HistoryMapper
import com.lexwilliam.domain.model.local.AnimeHistory
import com.lexwilliam.domain.model.local.SearchHistory
import com.lexwilliam.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
   private val historyLocalSource: HistoryLocalSource,
   private val historyMapper: HistoryMapper
): HistoryRepository {
    override fun getSearchHistory(): Flow<List<SearchHistory>> {
        return historyLocalSource.getSearchHistory().map { history -> history.map { historyMapper.toDomain(it) } }
    }

    override fun getAnimeHistory(): Flow<List<AnimeHistory>> {
        return historyLocalSource.getAnimeHistory().map { history -> history.map { historyMapper.toDomain(it) } }
    }

    override suspend fun deleteSearchHistory(query: String): Int {
        return historyLocalSource.deleteSearchHistory(query)
    }

    override suspend fun deleteAllSearch(): Int {
        return historyLocalSource.deleteAllSearch()
    }

    override suspend fun deleteAnimeByTitle(title: String): Int {
        return historyLocalSource.deleteAnimeByTitle(title)
    }

    override suspend fun deleteAllAnime(): Int {
        return historyLocalSource.deleteAllAnime()
    }

    override suspend fun insertSearch(search: SearchHistory): Long {
        return historyLocalSource.insertSearch(historyMapper.toRepo(search))
    }

    override suspend fun insertAnime(anime: AnimeHistory): Long {
        return historyLocalSource.insertAnime(historyMapper.toRepo(anime))
    }


}

