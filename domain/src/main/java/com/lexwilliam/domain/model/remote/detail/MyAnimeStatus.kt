package com.lexwilliam.domain.model.remote.detail

data class MyAnimeStatus(
    val status: String,
    val score: Int,
    val numEpisodesWatched: Int,
    val isRewatching: Boolean,
    val updatedAt: String
)