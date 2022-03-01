package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.local.AnimeHistory
import com.lexwilliam.domain.repository.HistoryRepository
import javax.inject.Inject

interface InsertAnimeHistory {
    suspend fun execute(anime: AnimeHistory): Long
}

class InsertAnimeHistoryImpl @Inject constructor(
    private val historyRepository: HistoryRepository
): InsertAnimeHistory {
    override suspend fun execute(anime: AnimeHistory): Long {
        return historyRepository.insertAnime(anime)
    }
}