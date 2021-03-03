package com.example.risuto.domain.model

data class TopItem(
    val mal_id: Int?,
    val rank: Int?,
    val title: String?,
    val url: String?,
    val image_url: String?,
    val type: String?,
    val episodes: Int?,
    val start_date: String?,
    val end_date: String? = "Still Ongoing",
    val members: Int?,
    val score: Float?
)