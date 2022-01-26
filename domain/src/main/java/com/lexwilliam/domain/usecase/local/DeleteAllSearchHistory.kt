package com.lexwilliam.domain.usecase.local

import com.lexwilliam.domain.repository.HistoryRepository
import javax.inject.Inject

interface DeleteAllSearchHistory {
    suspend fun execute(): Int
}

class DeleteAllSearchHistoryImpl @Inject constructor(
    private val historyRepository: HistoryRepository
): DeleteAllSearchHistory {
    override suspend fun execute(): Int {
        return historyRepository.deleteAllSearch()
    }
}