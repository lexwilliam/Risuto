package com.example.risuto.domain.model

import com.example.risuto.data.remote.model.detail.Genre
import com.example.risuto.data.remote.model.detail.Producer

data class Season(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val season_name: String,
    val season_year: Int,
    val anime: List<SeasonAnime>
)