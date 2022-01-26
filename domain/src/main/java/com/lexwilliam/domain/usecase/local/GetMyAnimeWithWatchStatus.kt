package com.lexwilliam.domain.usecase.local

import com.lexwilliam.domain.model.local.MyAnime
import com.lexwilliam.domain.model.local.WatchStatus
import com.lexwilliam.domain.repository.MyAnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMyAnimeWithWatchStatus {
    suspend fun execute(
        watchStatus: WatchStatus
    ): Flow<List<MyAnime>>
}

class GetMyAnimeWithWatchStatusImpl @Inject constructor(
    private val myAnimeRepository: MyAnimeRepository
): GetMyAnimeWithWatchStatus {
    override suspend fun execute(watchStatus: WatchStatus): Flow<List<MyAnime>> {
        return myAnimeRepository.getMyAnimeWithWatchStatus(watchStatus)
    }
}