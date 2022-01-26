package com.example.risuto.domain.repository

import com.example.risuto.data.local.model.WatchStatus
import com.lexwilliam.domain.model.MyAnime
import com.lexwilliam.domain.model.local.MyAnime
import com.lexwilliam.domain.model.local.WatchStatus
import kotlinx.coroutines.flow.Flow

interface MyAnimeRepository {

    suspend fun getMyAnimes(): Flow<List<MyAnime>>

    suspend fun getMyAnimeWithWatchStatus(watchStatus: WatchStatus): Flow<List<MyAnime>>

    suspend fun deleteMyAnime(myAnime: MyAnime): Flow<Int>

    suspend fun insert(myAnime: MyAnime): Flow<Long>

}