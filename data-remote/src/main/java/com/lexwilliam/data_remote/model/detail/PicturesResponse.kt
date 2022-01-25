package com.lexwilliam.data_remote.model.detail

data class PicturesResponse(
    val pictures: List<PictureResponse>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)

data class PictureResponse(
    val large: String,
    val small: String
)