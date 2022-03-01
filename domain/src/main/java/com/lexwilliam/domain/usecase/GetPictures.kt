package com.lexwilliam.domain.usecase

import com.lexwilliam.data.model.remote.detail.Pictures
import com.lexwilliam.domain.model.remote.detail.News
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPictures {
    suspend fun execute(id: Int): Flow<Pictures>
}

class GetPicturesImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetPictures {
    override suspend fun execute(id: Int): Flow<Pictures> {
        return detailRepository.pictures(id)
    }
}