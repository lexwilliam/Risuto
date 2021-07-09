package com.example.risuto.domain.usecase.local

import com.example.risuto.data.local.repository.HistoryRepository
import com.example.risuto.domain.model.history.SearchHistory
import javax.inject.Inject

class InsertSearchHistoryUseCase
@Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke(params: SearchHistory) = historyRepository.insertSearch(params)
}