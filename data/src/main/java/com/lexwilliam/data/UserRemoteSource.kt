package com.lexwilliam.data

interface UserRemoteSource {

    suspend fun getUserInfo(authHeader: String): String?

}