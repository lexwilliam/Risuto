package com.lexwilliam.domain.usecase.local

import com.lexwilliam.domain.repository.HistoryRepository
import javax.inject.Inject

interface DeleteSearchHistory {
    suspend fun execute(
        title: String
    ): Int
}

class DeleteSearchHistoryImpl @Inject constructor(
    private val historyRepository: HistoryRepository
): DeleteSearchHistory {
    override suspend fun execute(title: String): Int {
        return historyRepository.deleteSearchHistory(title)
    }
}