package com.lexwilliam.data.model.remote.season

import com.lexwilliam.domain.model.remote.season.SeasonAnime


data class SeasonRepo(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val season_name: String,
    val season_year: Int,
    val anime: List<SeasonAnimeRepo>
)