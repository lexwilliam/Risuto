package com.lexwilliam.data_remote.model.detail

data class EpisodesResponse(
    val episodes: List<EpisodeResponse>,
    val episodes_last_page: Int,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)

data class EpisodeResponse(
    val aired: String,
    val episode_id: Int,
    val filler: Boolean,
    val forum_url: String,
    val recap: Boolean,
    val title: String,
    val title_japanese: String,
    val title_romanji: String,
    val video_url: Any
)

