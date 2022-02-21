package com.lexwilliam.data.model.remote.detail

data class MyAnimeStatusRepo(
    val status: String,
    val score: Int,
    val numEpisodesWatched: Int,
    val isRewatching: Boolean,
    val updatedAt: String
)