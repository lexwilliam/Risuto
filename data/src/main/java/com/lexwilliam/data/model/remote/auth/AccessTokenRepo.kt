package com.lexwilliam.data.model.remote.auth

data class AccessTokenRepo(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val tokenType: String
)