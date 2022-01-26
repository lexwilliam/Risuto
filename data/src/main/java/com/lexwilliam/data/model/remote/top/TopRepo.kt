package com.lexwilliam.data.model.remote.top

import com.lexwilliam.domain.model.remote.top.TopAnime

data class TopRepo(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val top: List<TopAnimeRepo>
)