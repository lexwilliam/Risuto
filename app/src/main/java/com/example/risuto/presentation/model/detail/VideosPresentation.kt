package com.example.risuto.presentation.model.detail

import com.example.risuto.data.remote.model.detail.Promo

data class VideosPresentation(
    val episodes: List<Any>,
    val promo: List<Promo>
)