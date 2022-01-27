package com.lexwilliam.risutov2.model

import com.lexwilliam.domain.model.local.WatchStatus
import com.lexwilliam.risutov2.model.common.DemographicPresentation
import com.lexwilliam.risutov2.model.common.GenrePresentation
import com.lexwilliam.risutov2.model.common.ProducerPresentation
import com.lexwilliam.risutov2.model.common.ThemePresentation
import com.lexwilliam.risutov2.model.local.WatchStatusPresentation

data class AnimePresentation(
    val airing: Boolean? = false,
    val airing_start: String? = "",
    val continuing: Boolean? = false,
    val demographics: List<DemographicPresentation>? = emptyList(),
    val end_date: String?= "",
    val episodes: Int? = -1,
    val explicit_genres: List<Any>? = emptyList(),
    val genres: List<GenrePresentation>? = emptyList(),
    val image_url: String? = "",
    val kids: Boolean? = false,
    val licensors: List<Any>? = emptyList(),
    val mal_id: Int? = -1,
    val members: Int? = -1,
    val my_score: Int? = -1,
    val producers: List<ProducerPresentation>? = emptyList(),
    val rank: Int? = -1,
    val rated: String? = "",
    val r18: Boolean? = false,
    val start_date: String? = "",
    val score: Double? = -1.0,
    val source: String? = "",
    val synopsis: String? = "",
    val themes: List<ThemePresentation>? = emptyList(),
    val title: String? = "",
    val type: String? = "",
    val url: String? = "",
    val watch_status: WatchStatusPresentation? = WatchStatusPresentation.Default,
    val timeAdded: Long = -1L
)