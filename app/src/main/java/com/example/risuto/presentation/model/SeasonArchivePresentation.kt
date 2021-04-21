package com.example.risuto.presentation.model

import com.example.risuto.data.remote.model.detail.Archive

data class SeasonArchivePresentation(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val archive: List<Archive>
)