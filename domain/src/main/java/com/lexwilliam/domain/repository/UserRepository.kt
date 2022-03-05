package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.remote.user.UserAnimeList
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserInfo(): String?

    suspend fun getUserAnimeList(): Flow<UserAnimeList>

}