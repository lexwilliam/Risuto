package com.lexwilliam.domain.usecase.local

import com.lexwilliam.domain.model.local.MyAnime
import com.lexwilliam.domain.repository.MyAnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMyAnimes {
    suspend fun execute(): Flow<List<MyAnime>>
}

class GetMyAnimesImpl @Inject constructor(
    private val myAnimeRepository: MyAnimeRepository
): GetMyAnimes {
    override suspend fun execute(): Flow<List<MyAnime>> {
        return myAnimeRepository.getMyAnimes()
    }
}