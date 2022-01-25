package com.lexwilliam.risutov2.model

import com.lexwilliam.risutov2.model.AnimeListPresentation

data class SeasonPresentation(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val season_name: String,
    val season_year: Int,
    val anime: List<AnimeListPresentation>
)