package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.model.remote.detail.Forum
import com.lexwilliam.domain.model.remote.detail.MoreInfo
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMoreInfo {
    suspend fun execute(id: Int): Flow<MoreInfo>
}

class GetMoreInfoImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetMoreInfo {
    override suspend fun execute(id: Int): Flow<MoreInfo> {
        return detailRepository.moreInfo(id)
    }
}