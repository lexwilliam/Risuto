package com.example.risuto.data.local.dao

import androidx.room.*
import com.example.risuto.data.local.model.MyAnimeEntity
import com.example.risuto.data.local.model.WatchStatus

@Dao
interface MyAnimeDao {
    @Query("SELECT * FROM myAnime ORDER BY watchStatus DESC")
    suspend fun getMyAnimes(): List<MyAnimeEntity>

    @Query("SELECT * FROM myAnime WHERE watchStatus=:watchStatus ORDER BY timeAdded DESC")
    suspend fun getMyAnimeWithWatchStatus(watchStatus: WatchStatus): List<MyAnimeEntity>

    @Delete
    suspend fun deleteMyAnime(myAnime: MyAnimeEntity): Int

    @Update
    suspend fun updateMyAnime(myAnime: MyAnimeEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myAnimeEntity: MyAnimeEntity): Long
}