package com.lexwilliam.data_remote.model.detail

data class MoreInfoResponse(
    val moreinfo: String,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)