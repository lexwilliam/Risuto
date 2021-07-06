package com.example.risuto.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "animeHistory", indices = [Index(value = ["mal_id"], unique = true)])
data class AnimeHistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "mal_id") val mal_id : Int,
    val image_url : String,
    val title : String,
    val synopsis : String,
    val type : String,
    val episodes : Int,
    val score : Double,
    val members : Int,
    val timeAdded: Long = System.currentTimeMillis()
)