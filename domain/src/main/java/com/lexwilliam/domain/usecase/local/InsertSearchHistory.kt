package com.lexwilliam.domain.usecase.local

import com.lexwilliam.domain.model.local.AnimeHistory
import com.lexwilliam.domain.model.local.SearchHistory
import com.lexwilliam.domain.repository.HistoryRepository
import javax.inject.Inject

interface InsertSearchHistory {
    suspend fun execute(search: SearchHistory): Long
}

class InsertSearchHistoryImpl @Inject constructor(
    private val historyRepository: HistoryRepository
): InsertSearchHistory {
    override suspend fun execute(search: SearchHistory): Long {
        return historyRepository.insertSearch(search)
    }
}