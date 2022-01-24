package com.example.risuto.presentation.model

import com.example.risuto.domain.model.SeasonAnime

data class SeasonPresentation(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val season_name: String,
    val season_year: Int,
    val anime: List<AnimeListPresentation>
)