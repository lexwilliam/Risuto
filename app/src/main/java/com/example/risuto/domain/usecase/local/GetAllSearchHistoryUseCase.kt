package com.example.risuto.domain.usecase.local

import com.example.risuto.data.local.repository.HistoryRepository
import javax.inject.Inject

class GetAllSearchHistoryUseCase
@Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke() = historyRepository.getSearchHistory()
}