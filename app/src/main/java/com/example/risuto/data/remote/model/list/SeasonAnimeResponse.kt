package com.example.risuto.data.remote.model.list

data class SeasonAnimeResponse(
    val airing_start: String,
    val continuing: Boolean,
    val demographics: List<Demographic>,
    val episodes: Int?,
    val explicit_genres: List<Any>,
    val genres: List<Genre>,
    val image_url: String,
    val kids: Boolean,
    val licensors: List<Any>,
    val mal_id: Int,
    val members: Int,
    val producers: List<Producer>,
    val r18: Boolean,
    val score: Double?,
    val source: String,
    val synopsis: String,
    val themes: List<Theme>,
    val title: String,
    val type: String?,
    val url: String
)

data class Theme(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Producer(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Genre(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Demographic(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)