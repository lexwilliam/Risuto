package com.lexwilliam.data_remote.model.top

import com.lexwilliam.data_remote.model.top.TopAnimeResponse

data class TopResponse(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val top: List<TopAnimeResponse>
)