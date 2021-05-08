package com.example.risuto.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animeHistory")
data class AnimeHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mal_id: Int,
    val title: String,
    val image_url: String
)