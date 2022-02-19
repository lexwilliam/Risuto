package com.lexwilliam.data_remote.model.auth

import com.squareup.moshi.Json

data class AccessTokenResponse(
    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "expires_in")
    val expiresIn: Int,
    @Json(name = "refresh_token")
    val refreshToken: String,
    @Json(name = "token_type")
    val tokenType: String
)