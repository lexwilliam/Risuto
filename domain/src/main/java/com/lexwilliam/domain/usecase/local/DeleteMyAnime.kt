package com.lexwilliam.domain.usecase.local

import com.lexwilliam.domain.model.local.MyAnime
import com.lexwilliam.domain.repository.MyAnimeRepository
import javax.inject.Inject

interface DeleteMyAnime {
    suspend fun execute(
        myAnime: MyAnime
    ): Int
}

class DeleteMyAnimeImpl @Inject constructor(
    private val myAnimeRepository: MyAnimeRepository
): DeleteMyAnime {
    override suspend fun execute(myAnime: MyAnime): Int {
        return myAnimeRepository.deleteMyAnime(myAnime)
    }
}