package com.example.risuto.domain.usecase.local

import com.example.risuto.data.local.repository.HistoryRepository
import com.example.risuto.data.local.repository.MyAnimeRepository
import javax.inject.Inject

class DeleteMyAnimeUseCase
@Inject constructor(
    private val myAnimeRepository: MyAnimeRepository
) {
    suspend operator fun invoke(malId: Int) = myAnimeRepository.deleteMyAnime(malId)
}