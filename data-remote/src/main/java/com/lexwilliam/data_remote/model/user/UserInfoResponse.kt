package com.lexwilliam.data_remote.model.user

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class UserInfoResponse(
    val id: Int,
    val name: String,
    val location: String,
    val joined_at: String
)