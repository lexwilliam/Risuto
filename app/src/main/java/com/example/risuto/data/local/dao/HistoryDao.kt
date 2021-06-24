package com.example.risuto.data.local.dao

import androidx.room.*
import com.example.risuto.data.local.Results
import com.example.risuto.data.local.mapper.toEntity
import com.example.risuto.data.local.model.AnimeHistoryEntity
import com.example.risuto.data.local.model.SearchHistoryEntity
import com.example.risuto.domain.model.AnimeHistory
import com.example.risuto.domain.model.SearchHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM searchHistory ORDER BY id DESC LIMIT 5")
    fun getSearchHistory(): Flow<List<SearchHistoryEntity>>

    @Query("DELETE FROM searchHistory WHERE `query`=:query")
    suspend fun deleteSearch(query: String): Int

    @Query("DELETE FROM searchHistory")
    suspend fun deleteAllSearchHistory(): Int

    @Query("SELECT * FROM animeHistory ORDER BY id DESC")
    fun getAnimeHistory(): Flow<List<AnimeHistoryEntity>>

    @Query("DELETE FROM animeHistory WHERE title=:title")
    suspend fun deleteAnime(title: String): Int

    @Query("DELETE FROM animeHistory")
    suspend fun deleteAllAnimeHistory(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchHistoryEntity: SearchHistoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(animeHistoryEntity: AnimeHistoryEntity)

    @Transaction
    suspend fun insert(search: SearchHistory): Results {
        return try {
            insert(search.toEntity())
            Results.SUCCESS
        } catch (e: Exception) {
            Results.FAILURE
        }
    }

    @Transaction
    suspend fun insert(anime: AnimeHistory): Results {
        return try {
            insert(anime.toEntity())
            Results.SUCCESS
        } catch (e: Exception) {
            Results.FAILURE
        }
    }
}

