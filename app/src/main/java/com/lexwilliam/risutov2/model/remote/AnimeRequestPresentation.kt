package com.lexwilliam.risutov2.model.remote

import com.lexwilliam.risutov2.model.AnimePresentation

data class AnimeRequestPresentation(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val season_name: String? = "",
    val season_year: Int? = -1,
    val anime: List<AnimePresentation>,
    val last_page: Int? = -1
)