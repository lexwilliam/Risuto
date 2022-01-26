package com.lexwilliam.data.model.remote.detail

data class Pictures(
    val pictures: List<Picture>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)

data class Picture(
    val large: String,
    val small: String
)