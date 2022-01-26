package com.lexwilliam.domain.model.remote.top

data class Top(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val top: List<TopAnime>
)