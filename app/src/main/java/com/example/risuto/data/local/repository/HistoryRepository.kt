package com.example.risuto.data.local.repository

import com.example.risuto.data.local.Results
import com.example.risuto.data.local.dao.HistoryDao
import com.example.risuto.data.local.mapper.toDomain
import com.example.risuto.domain.model.AnimeHistory
import com.example.risuto.domain.model.SearchHistory
import com.example.risuto.domain.repository.IHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryRepository(private val historyDao: HistoryDao): IHistoryRepository {

    override suspend fun getSearchHistory(): Flow<List<SearchHistory>> = flow {
        val searches = historyDao.getSearchHistory()
        emit(searches.map { it.toDomain() })
    }

    override suspend fun getAnimeHistory(): Flow<List<AnimeHistory>> = flow {
        val animes = historyDao.getAnimeHistory()
        emit(animes.map { it.toDomain() })
    }

    override suspend fun deleteSearchHistory(query: String): Flow<Int> = flow {
        val affectedQuery = historyDao.deleteSearch(query)
        emit(affectedQuery)
    }

    override suspend fun deleteAllSearch(): Flow<Int> = flow {
        val deleteAll = historyDao.deleteAllSearchHistory()
        emit(deleteAll)
    }

    override suspend fun deleteAnimeByTitle(title: String): Flow<Int> = flow {
        val affectedQuery = historyDao.deleteAnime(title = title)
        emit(affectedQuery)
    }

    override suspend fun deleteAllAnime(): Flow<Int> = flow {
        val deleteAll = historyDao.deleteAllAnimeHistory()
        emit(deleteAll)
    }

    override suspend fun insertSearch(search: SearchHistory): Flow<Results> = flow {
        val result = historyDao.insert(search = search)
        emit(result)
    }

    override suspend fun insertAnime(anime: AnimeHistory): Flow<Results> = flow {
        val result = historyDao.insert(anime = anime)
        emit(result)
    }

}

