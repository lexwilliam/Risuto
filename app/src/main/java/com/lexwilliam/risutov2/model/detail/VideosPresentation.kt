package com.lexwilliam.risutov2.model.detail

data class VideosPresentation(
    val episodes: List<Any>,
    val promo: List<PromoPresentation>
)

data class PromoPresentation(
    val image_url: String,
    val title: String,
    val video_url: String
)