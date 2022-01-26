package com.lexwilliam.domain.model.remote.season

import com.lexwilliam.domain.model.common.Demographic
import com.lexwilliam.domain.model.common.Genre
import com.lexwilliam.domain.model.common.Producer
import com.lexwilliam.domain.model.common.Theme

data class SeasonAnime(
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