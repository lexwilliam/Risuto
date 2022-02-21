package com.lexwilliam.data_remote.model.detail

import com.squareup.moshi.Json

data class MyAnimeStatusResponse(
    val status: String,
    val score: Int,
    @Json(name = "num_episodes_watched")
    val numEpisodesWatched: Int,
    @Json(name = "is_rewatching")
    val isRewatching: Boolean,
    @Json(name = "updated_at")
    val updatedAt: String
)