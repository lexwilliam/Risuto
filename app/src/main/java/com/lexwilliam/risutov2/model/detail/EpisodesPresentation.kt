package com.lexwilliam.risutov2.model.detail

import com.example.risuto.data.remote.model.detail.Episode

data class EpisodesPresentation(
    val episodes: List<Episode>,
    val episodes_last_page: Int
)