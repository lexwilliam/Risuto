package com.lexwilliam.domain.model.remote.detail

data class MoreInfo(
    val moreinfo: String,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)