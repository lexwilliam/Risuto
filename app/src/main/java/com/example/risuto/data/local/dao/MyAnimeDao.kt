package com.example.risuto.data.local.dao

import androidx.room.*
import com.example.risuto.data.local.Results
import com.example.risuto.data.local.mapper.toEntity
import com.example.risuto.data.local.model.MyAnimeEntity
import com.example.risuto.data.local.model.WatchStatus
import com.example.risuto.domain.model.MyAnime
import com.example.risuto.domain.model.SearchHistory

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

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insert(myAnimeEntity: MyAnimeEntity): Long
}