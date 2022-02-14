package com.lexwilliam.data_remote.model.user

data class UserInfoResponse(
    val anime_statistics: AnimeStatisticsResponse,
    val id: Int,
    val joined_at: String,
    val location: String,
    val name: String
)