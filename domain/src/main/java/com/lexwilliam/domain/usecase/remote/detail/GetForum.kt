package com.lexwilliam.domain.usecase.remote.detail

import com.lexwilliam.domain.model.remote.detail.Episodes
import com.lexwilliam.domain.model.remote.detail.Forum
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetForum {
    suspend fun execute(id: Int): Flow<Forum>
}

class GetForumImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetForum {
    override suspend fun execute(id: Int): Flow<Forum> {
        return detailRepository.forum(id)
    }
}