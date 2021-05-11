package com.example.risuto.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "animeHistory")
data class AnimeHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mal_id : Int,
    val image_url : String,
    val title : String,
    val synopsis : String,
    val type : String,
    val episodes : Int,
    val score : Double,
    val members : Int
)