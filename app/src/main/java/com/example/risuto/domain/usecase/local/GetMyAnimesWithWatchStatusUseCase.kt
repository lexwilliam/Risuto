package com.example.risuto.domain.usecase.local

import com.example.risuto.data.local.model.WatchStatus
import com.example.risuto.data.local.repository.MyAnimeRepository
import javax.inject.Inject

class GetMyAnimesWithWatchStatusUseCase
@Inject constructor(
    private val myAnimeRepository: MyAnimeRepository
) {
    suspend operator fun invoke(watchStatus: WatchStatus) = myAnimeRepository.getMyAnimeWithWatchStatus(watchStatus)
}