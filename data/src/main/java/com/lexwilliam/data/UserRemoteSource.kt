package com.lexwilliam.data

import com.lexwilliam.data.model.remote.user.UserAnimeListRepo
import com.lexwilliam.data.model.remote.user.UserAnimeUpdateRepo
import kotlinx.coroutines.flow.Flow

interface UserRemoteSource {

    suspend fun getUserInfo(authHeader: String): String?

    suspend fun getUserAnimeList(authHeader: String): Flow<UserAnimeListRepo>

//    suspend fun updateUserAnimeStatus(authHeader: String, id: Int): Flow<UserAnimeUpdateRepo>

}