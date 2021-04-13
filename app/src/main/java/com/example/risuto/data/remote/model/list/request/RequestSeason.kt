package com.example.risuto.data.remote.model.list.request

import com.example.risuto.data.remote.model.list.SeasonAnimeResponse

data class RequestSeason(
    val anime: List<SeasonAnimeResponse>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val season_name: String,
    val season_year: Int
)