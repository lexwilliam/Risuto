package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.local.SearchHistory
import com.lexwilliam.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSearchHistory {
    suspend fun execute(): Flow<List<SearchHistory>>
}

class GetSearchHistoryImpl @Inject constructor(
    private val historyRepository: HistoryRepository
): GetSearchHistory {
    override suspend fun execute(): Flow<List<SearchHistory>> {
        return historyRepository.getSearchHistory()
    }
}