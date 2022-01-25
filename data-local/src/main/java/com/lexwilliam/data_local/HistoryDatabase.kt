package com.lexwilliam.data_local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lexwilliam.data_local.dao.HistoryDao
import com.lexwilliam.data_local.model.AnimeHistoryEntity
import com.lexwilliam.data_local.model.SearchHistoryEntity

@Database(entities = [AnimeHistoryEntity::class, SearchHistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}