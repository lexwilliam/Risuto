package com.lexwilliam.domain.usecase.remote.detail

import com.lexwilliam.domain.model.remote.detail.MoreInfo
import com.lexwilliam.domain.model.remote.detail.MyAnimeStatus
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface GetMyAnimeStatus {
    suspend fun execute(accessToken: String, id: Int): Flow<MyAnimeStatus>
}

class GetMyAnimeStatusImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetMyAnimeStatus {
    override suspend fun execute(accessToken: String, id: Int): Flow<MyAnimeStatus> {
        return detailRepository.status(accessToken, id)
    }
}