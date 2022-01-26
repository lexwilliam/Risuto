package com.lexwilliam.data.model.remote.season

import com.lexwilliam.domain.model.remote.season.Archive

data class SeasonArchiveRepo(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val archive: List<Archive>
)

data class ArchiveRepo(
    val year: Int,
    val season: List<String>
)