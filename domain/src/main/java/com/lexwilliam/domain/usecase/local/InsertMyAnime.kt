package com.lexwilliam.domain.usecase.local

import com.lexwilliam.domain.model.local.MyAnime
import com.lexwilliam.domain.repository.MyAnimeRepository
import javax.inject.Inject

interface InsertMyAnime {
    suspend fun execute(
        myAnime: MyAnime
    ): Long
}

class InsertMyAnimeImpl @Inject constructor(
    private val myAnimeRepository: MyAnimeRepository
): InsertMyAnime {
    override suspend fun execute(myAnime: MyAnime): Long {
        return myAnimeRepository.insertMyAnime(myAnime)
    }
}