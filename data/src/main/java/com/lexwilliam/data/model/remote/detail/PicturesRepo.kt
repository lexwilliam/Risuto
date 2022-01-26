package com.lexwilliam.data.model.remote.detail

data class PicturesRepo(
    val pictures: List<PictureRepo>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)

data class PictureRepo(
    val large: String,
    val small: String
)