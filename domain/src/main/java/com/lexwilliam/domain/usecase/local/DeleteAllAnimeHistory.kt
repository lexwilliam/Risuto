package com.lexwilliam.domain.usecase.local

import com.lexwilliam.domain.repository.HistoryRepository
import javax.inject.Inject

interface DeleteAllAnimeHistory {
    suspend fun execute(): Int
}

class DeleteAllAnimeHistoryImpl @Inject constructor(
    private val historyRepository: HistoryRepository
): DeleteAllAnimeHistory {
    override suspend fun execute(): Int {
        return historyRepository.deleteAllAnime()
    }
}