package com.example.risuto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.risuto.data.local.dao.HistoryDao
import com.example.risuto.data.local.model.AnimeHistoryEntity
import com.example.risuto.data.local.model.SearchHistoryEntity

@Database(entities = [AnimeHistoryEntity::class, SearchHistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}