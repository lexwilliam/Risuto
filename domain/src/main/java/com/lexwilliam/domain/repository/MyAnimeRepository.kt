package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.local.MyAnime
import com.lexwilliam.domain.model.local.WatchStatus
import kotlinx.coroutines.flow.Flow

interface MyAnimeRepository {

    fun getMyAnimes(): Flow<List<MyAnime>>

    fun getMyAnimeWithWatchStatus(watchStatus: WatchStatus): Flow<List<MyAnime>>

    suspend fun deleteMyAnime(myAnime: MyAnime): Int

    suspend fun updateMyAnime(myAnime: MyAnime): Int

    suspend fun insertMyAnime(myAnime: MyAnime): Long

}