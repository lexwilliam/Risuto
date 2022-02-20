package com.lexwilliam.data

import com.lexwilliam.data.model.remote.user.UserAnimeListRepo
import kotlinx.coroutines.flow.Flow

interface UserRemoteSource {

    suspend fun getUserInfo(authHeader: String): String?

    suspend fun getUserAnimeList(authHeader: String): Flow<UserAnimeListRepo>

}