package com.example.risuto.domain.model

import androidx.room.PrimaryKey

data class AnimeHistory(
    val mal_id: Int,
    val title: String,
    val image_url: String
)