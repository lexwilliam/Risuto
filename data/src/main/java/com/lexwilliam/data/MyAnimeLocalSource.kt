package com.lexwilliam.data

import com.lexwilliam.data.model.local.MyAnimeRepo
import com.lexwilliam.data.model.local.WatchStatusRepo
import kotlinx.coroutines.flow.Flow

interface MyAnimeLocalSource {

    fun getMyAnimes(): Flow<List<MyAnimeRepo>>

    fun getMyAnimeWithWatchStatus(watchStatus: WatchStatusRepo): Flow<List<MyAnimeRepo>>

    suspend fun deleteMyAnime(myAnime: MyAnimeRepo): Int

    suspend fun updateMyAnime(myAnime: MyAnimeRepo): Int

    suspend fun insertMyAnime(myAnime: MyAnimeRepo): Long
}