package com.example.risuto.data.remote.model.list.request

import com.example.risuto.data.remote.model.list.TopAnimeResponse

data class RequestTop(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val top: List<TopAnimeResponse>
)