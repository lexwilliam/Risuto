package com.lexwilliam.data_local.dao

import androidx.room.*
import com.lexwilliam.data_local.model.AnimeHistoryEntity
import com.lexwilliam.data_local.model.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM searchHistory LIMIT 5")
    fun getSearchHistory(): Flow<List<SearchHistoryEntity>>

    @Query("DELETE FROM searchHistory WHERE `query`=:query")
    suspend fun deleteSearch(query: String): Int

    @Query("DELETE FROM searchHistory")
    suspend fun deleteAllSearchHistory(): Int

    @Query("SELECT * FROM animeHistory ORDER BY timeAdded DESC")
    fun getAnimeHistory(): Flow<List<AnimeHistoryEntity>>

    @Query("DELETE FROM animeHistory WHERE title=:title")
    suspend fun deleteAnime(title: String): Int

    @Query("DELETE FROM animeHistory")
    suspend fun deleteAllAnimeHistory(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchHistoryEntity: SearchHistoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(animeHistoryEntity: AnimeHistoryEntity): Long
}

