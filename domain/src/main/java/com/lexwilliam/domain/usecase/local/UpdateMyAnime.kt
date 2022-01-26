package com.lexwilliam.domain.usecase.local

import com.lexwilliam.domain.model.local.MyAnime
import com.lexwilliam.domain.repository.MyAnimeRepository
import javax.inject.Inject

interface UpdateMyAnime {
    suspend fun execute(
        myAnime: MyAnime
    ): Int
}

class UpdateMyAnimeImpl @Inject constructor(
    private val myAnimeRepository: MyAnimeRepository
): UpdateMyAnime {
    override suspend fun execute(myAnime: MyAnime): Int {
        return myAnimeRepository.updateMyAnime(myAnime)
    }
}