package com.example.risuto.domain.usecase.local

import com.example.risuto.data.local.repository.HistoryRepository
import com.example.risuto.domain.model.history.AnimeHistory
import javax.inject.Inject

class InsertAnimeHistoryUseCase
@Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke(params: AnimeHistory) = historyRepository.insertAnime(params)
}