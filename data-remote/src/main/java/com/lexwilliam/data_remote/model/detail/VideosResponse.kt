package com.lexwilliam.data_remote.model.detail

data class VideosResponse(
    val episodes: List<Any>,
    val promo: List<PromoResponse>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)

data class PromoResponse(
    val image_url: String,
    val title: String,
    val video_url: String
)