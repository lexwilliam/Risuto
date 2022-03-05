package com.lexwilliam.data.model.remote.user

data class UserAnimeUpdateRepo(
    val comments: String,
    val isReWatching: Boolean,
    val numTimesReWatched: Int,
    val numWatchedEpisodes: Int,
    val priority: Int,
    val reWatchValue: Int,
    val score: Int,
    val status: String,
    val tags: List<String>,
    val updatedAt: String
)