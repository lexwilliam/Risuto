package com.lexwilliam.data_local.data

import com.lexwilliam.data.HistoryLocalSource
import com.lexwilliam.data.model.local.AnimeHistoryRepo
import com.lexwilliam.data.model.local.SearchHistoryRepo
import com.lexwilliam.data_local.dao.HistoryDao
import com.lexwilliam.data_local.mapper.HistoryMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HistoryLocalSourceImpl @Inject constructor(
    private val historyDao: HistoryDao,
    private val historyMapper: HistoryMapper
): HistoryLocalSource {
    override fun getSearchHistory(): Flow<List<SearchHistoryRepo>> {
        return historyDao.getSearchHistory().map { history -> history.map { historyMapper.toRepo(it) } }
    }

    override fun getAnimeHistory(): Flow<List<AnimeHistoryRepo>> {
        return historyDao.getAnimeHistory().map { anime -> anime.map { historyMapper.toRepo(it) } }
    }

    override suspend fun deleteSearchHistory(query: String): Int {
        return historyDao.deleteSearch(query)
    }

    override suspend fun deleteAllSearch(): Int {
        return historyDao.deleteAllSearchHistory()
    }

    override suspend fun deleteAnimeByTitle(title: String): Int {
        return historyDao.deleteAnime(title)
    }

    override suspend fun deleteAllAnime(): Int {
        return historyDao.deleteAllAnimeHistory()
    }

    override suspend fun insertSearch(search: SearchHistoryRepo): Long {
        return historyDao.insert(historyMapper.toEntity(search))
    }

    override suspend fun insertAnime(anime: AnimeHistoryRepo): Long {
        return historyDao.insert(historyMapper.toEntity(anime))
    }
}