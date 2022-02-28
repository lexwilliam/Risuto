package com.lexwilliam.domain.usecase.local.history

import com.lexwilliam.domain.model.local.AnimeHistory
import com.lexwilliam.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAnimeHistory {
    suspend fun execute(): Flow<List<AnimeHistory>>
}

class GetAnimeHistoryImpl @Inject constructor(
    private val historyRepository: HistoryRepository
): GetAnimeHistory {
    override suspend fun execute(): Flow<List<AnimeHistory>> {
        return historyRepository.getAnimeHistory()
    }
}