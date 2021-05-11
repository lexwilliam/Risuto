package com.example.risuto.domain.model

data class AnimeHistory(
    val mal_id: Int,
    val image_url: String,
    val title: String,
    val synopsis: String,
    val type: String,
    val episodes: Int,
    val score: Double,
    val members: Int
)