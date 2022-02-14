package com.lexwilliam.domain.model.remote.auth

data class AccessToken(
    val accessToken: String?,
    val expiresIn: Int?,
    val refreshToken: String?,
    val tokenType: String
)