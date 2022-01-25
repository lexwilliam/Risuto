package com.lexwilliam.data_remote.model.season

import com.lexwilliam.data_remote.model.common.DemographicResponse
import com.lexwilliam.data_remote.model.common.GenreResponse
import com.lexwilliam.data_remote.model.common.ProducerResponse
import com.lexwilliam.data_remote.model.common.ThemeResponse

data class SeasonAnimeResponse(
    val airing_start: String,
    val continuing: Boolean,
    val demographics: List<DemographicResponse>,
    val episodes: Int?,
    val explicit_genres: List<Any>,
    val genres: List<GenreResponse>,
    val image_url: String,
    val kids: Boolean,
    val licensors: List<Any>,
    val mal_id: Int,
    val members: Int,
    val producers: List<ProducerResponse>,
    val r18: Boolean,
    val score: Double?,
    val source: String,
    val synopsis: String,
    val themes: List<ThemeResponse>,
    val title: String,
    val type: String?,
    val url: String
)