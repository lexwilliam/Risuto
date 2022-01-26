package com.lexwilliam.data.model.remote.season

data class SeasonArchiveRepo(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val archive: List<ArchiveRepo>
)

data class ArchiveRepo(
    val year: Int,
    val season: List<String>
)