package com.lexwilliam.data_local.dao

import androidx.room.*
import com.lexwilliam.data_local.model.MyAnimeEntity
import com.lexwilliam.data_local.model.WatchStatusEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyAnimeDao {
    @Query("SELECT * FROM myAnime ORDER BY watchStatus DESC")
    fun getMyAnimes(): Flow<List<MyAnimeEntity>>

    @Query("SELECT * FROM myAnime WHERE watchStatus=:watchStatus ORDER BY timeAdded DESC")
    fun getMyAnimeWithWatchStatus(watchStatus: WatchStatusEntity): Flow<List<MyAnimeEntity>>

    @Delete
    suspend fun deleteMyAnime(myAnime: MyAnimeEntity): Int

    @Update
    suspend fun updateMyAnime(myAnime: MyAnimeEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyAnime(myAnimeEntity: MyAnimeEntity): Long
}