package com.lexwilliam.domain.repository

interface UserRepository {

    suspend fun getUserInfo(accessToken: String): String?

}