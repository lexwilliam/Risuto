package com.example.risuto.domain.model

import com.example.risuto.data.remote.model.detail.Genre
import com.example.risuto.data.remote.model.detail.Producer

data class SeasonAnime(
    val airing_start: String,
    val continuing: Boolean,
    val episodes: Int,
    val genres: List<Genre>,
    val image_url: String,
    val kids: Boolean,
    val licensors: List<String>,
    val mal_id: Int,
    val members: Int,
    val producers: List<Producer>,
    val r18: Boolean,
    val score: Float,
    val source: String,
    val synopsis: String,
    val title: String,
    val type: String,
    val url: String
)