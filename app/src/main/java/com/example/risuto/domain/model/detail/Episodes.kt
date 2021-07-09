package com.example.risuto.domain.model.detail

import com.example.risuto.data.remote.model.detail.Episode

data class Episodes(
    val episodes: List<Episode>,
    val episodes_last_page: Int
)