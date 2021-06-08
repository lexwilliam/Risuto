package com.example.risuto.domain.repository

import com.example.risuto.data.local.Results
import com.example.risuto.data.local.model.WatchStatus
import com.example.risuto.domain.model.MyAnime
import kotlinx.coroutines.flow.Flow

interface IMyAnimeRepository {

    suspend fun getMyAnimes(): Flow<List<MyAnime>>

    suspend fun getMyAnimeWithWatchStatus(watchStatus: WatchStatus): Flow<List<MyAnime>>

    suspend fun deleteMyAnime(malId: Int): Flow<Int>

    suspend fun insert(myAnime: MyAnime): Flow<Results>

}