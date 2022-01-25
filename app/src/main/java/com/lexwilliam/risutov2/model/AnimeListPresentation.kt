package com.lexwilliam.risutov2.model

import com.example.risuto.data.local.model.WatchStatus

data class AnimeListPresentation(
    val mal_id: Int,
    val image_url: String,
    val title: String,
    val synopsis: String,
    val type: String,
    val episodes: Int,
    val score: Double,
    val members: Int,
)