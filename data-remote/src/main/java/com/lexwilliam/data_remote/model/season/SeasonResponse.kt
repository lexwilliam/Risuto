package com.lexwilliam.data_remote.model.season

data class SeasonResponse(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val season_name: String,
    val season_year: Int,
    val anime: List<SeasonAnimeResponse>
)