package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.anime.AnimeStaff
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAnimeStaff {
    suspend fun execute(id: Int): Flow<AnimeStaff>
}

class GetAnimeStaffImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetAnimeStaff {
    override suspend fun execute(id: Int): Flow<AnimeStaff> {
        return detailRepository.getAnimeStaff(id)
    }
}