package com.lexwilliam.data.model.season

import com.lexwilliam.data.model.common.*

data class SeasonAnimeRepo(
    val airing_start: String,
    val continuing: Boolean,
    val demographics: List<DemographicRepo>,
    val episodes: Int?,
    val explicit_genres: List<Any>,
    val genres: List<GenreRepo>,
    val image_url: String,
    val kids: Boolean,
    val licensors: List<Any>,
    val mal_id: Int,
    val members: Int,
    val producers: List<ProducerRepo>,
    val r18: Boolean,
    val score: Double?,
    val source: String,
    val synopsis: String,
    val themes: List<ThemeRepo>,
    val title: String,
    val type: String?,
    val url: String
)