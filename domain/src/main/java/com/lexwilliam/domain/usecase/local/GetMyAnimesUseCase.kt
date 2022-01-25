package com.example.risuto.domain.usecase.local

import com.example.risuto.data.local.repository.HistoryRepository
import com.example.risuto.data.local.repository.MyAnimeRepository
import javax.inject.Inject

class GetMyAnimesUseCase
@Inject constructor(
    private val myAnimeRepository: MyAnimeRepository
) {
    suspend operator fun invoke() = myAnimeRepository.getMyAnimes()
}