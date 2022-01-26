package com.lexwilliam.domain.model.remote.season

data class SeasonArchive(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val archive: List<Archive>
)

data class Archive(
    val year: Int,
    val season: List<String>
)