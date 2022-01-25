package com.lexwilliam.data.model.top

data class TopRepo(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val top: List<TopAnimeRepo>
)