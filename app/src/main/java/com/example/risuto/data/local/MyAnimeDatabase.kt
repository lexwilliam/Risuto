package com.example.risuto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.risuto.data.local.dao.MyAnimeDao
import com.example.risuto.data.local.model.MyAnimeEntity

@Database(entities = [MyAnimeEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyAnimeDatabase: RoomDatabase() {
    abstract fun myAnimeDao(): MyAnimeDao
}