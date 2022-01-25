package com.lexwilliam.domain.model

import com.example.risuto.data.remote.model.list.Demographic
import com.example.risuto.data.remote.model.list.Genre
import com.example.risuto.data.remote.model.list.Producer
import com.example.risuto.data.remote.model.list.Theme

data class SeasonAnime(
    val airing_start: String,
    val continuing: Boolean,
    val demographics: List<Demographic>,
    val episodes: Int,
    val explicit_genres: List<Any>,
    val genres: List<Genre>,
    val image_url: String,
    val kids: Boolean,
    val licensors: List<Any>,
    val mal_id: Int,
    val members: Int,
    val producers: List<Producer>,
    val r18: Boolean,
    val score: Double,
    val source: String,
    val synopsis: String,
    val themes: List<Theme>,
    val title: String,
    val type: String,
    val url: String
)