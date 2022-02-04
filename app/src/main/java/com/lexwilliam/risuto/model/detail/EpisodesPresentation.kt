package com.lexwilliam.risuto.model.detail

data class EpisodesPresentation(
    val episodes: List<EpisodePresentation>,
    val episodes_last_page: Int
)

data class EpisodePresentation(
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