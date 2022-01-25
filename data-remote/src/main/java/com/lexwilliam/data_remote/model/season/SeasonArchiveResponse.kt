package com.lexwilliam.data_remote.model.season

data class SeasonArchiveResponse(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val archive: List<ArchiveResponse>
)

data class ArchiveResponse(
    val year: Int,
    val season: List<String>
)