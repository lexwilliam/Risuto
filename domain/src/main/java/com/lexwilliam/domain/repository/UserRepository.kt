package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.remote.user.UserAnimeList
import com.lexwilliam.domain.model.remote.user.UserAnimeUpdate
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserInfo(): String?

    suspend fun getUserAnimeList(): Flow<UserAnimeList>

    suspend fun updateUserAnimeStatus(id: Int, numEpisodesWatched: Int, status: String, score: Int): Flow<UserAnimeUpdate>

    suspend fun deleteUserAnimeStatus(id: Int)
}