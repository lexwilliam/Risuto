package com.lexwilliam.data.model.remote.detail

import com.lexwilliam.domain.model.remote.detail.Promo

data class VideosRepo(
    val episodes: List<Any>,
    val promo: List<PromoRepo>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)

data class PromoRepo(
    val image_url: String,
    val title: String,
    val video_url: String
)