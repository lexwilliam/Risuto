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
    @Query("SELECT * FROM myAnime ORDER BY id DESC")
    suspend fun getMyAnimes(): List<MyAnimeEntity>

    @Query("SELECT * FROM myAnime WHERE watchStatus=:watchStatus ORDER BY id DESC")
    suspend fun getMyAnimeWithWatchStatus(watchStatus: WatchStatus): List<MyAnimeEntity>

    @Query("DELETE FROM myAnime WHERE mal_id=:malId")
    suspend fun deleteMyAnime(malId: Int): Int

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insert(myAnimeEntity: MyAnimeEntity)

    @Transaction
    suspend fun insert(myAnime: MyAnime): Results {
        return try {
            insert(myAnime.toEntity())
            Results.SUCCESS
        } catch (e: Exception) {
            Results.FAILURE
        }
    }
}