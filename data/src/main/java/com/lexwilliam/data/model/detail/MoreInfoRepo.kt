package com.lexwilliam.data.model.detail

data class MoreInfoRepo(
    val moreinfo: String,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)