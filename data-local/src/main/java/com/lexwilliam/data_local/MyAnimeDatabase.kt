package com.lexwilliam.data_local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lexwilliam.data_local.Converters
import com.lexwilliam.data_local.dao.MyAnimeDao
import com.lexwilliam.data_local.model.MyAnimeEntity

@Database(entities = [MyAnimeEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyAnimeDatabase: RoomDatabase() {
    abstract fun myAnimeDao(): MyAnimeDao
}