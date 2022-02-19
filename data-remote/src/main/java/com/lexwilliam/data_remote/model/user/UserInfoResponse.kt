package com.lexwilliam.data_remote.model.user

data class UserInfoResponse(
    val id: Int,
    val name: String,
    val location: String,
    val joined_at: String
)