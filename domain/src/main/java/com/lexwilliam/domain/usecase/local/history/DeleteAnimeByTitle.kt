package com.lexwilliam.domain.usecase.local.history

import com.lexwilliam.domain.repository.HistoryRepository
import javax.inject.Inject

interface DeleteAnimeByTitle {
    suspend fun execute(
        title: String
    ): Int
}

class DeleteAnimeByTitleImpl @Inject constructor(
    private val historyRepository: HistoryRepository
): DeleteAnimeByTitle {
    override suspend fun execute(title: String): Int {
        return historyRepository.deleteAnimeByTitle(title)
    }
}