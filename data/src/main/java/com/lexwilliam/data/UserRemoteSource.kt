package com.lexwilliam.data

import com.lexwilliam.data.model.remote.user.UserAnimeListRepo
import com.lexwilliam.data.model.remote.user.UserAnimeUpdateRepo
import com.lexwilliam.data.model.remote.user.UserProfileRepo
import kotlinx.coroutines.flow.Flow

interface UserRemoteSource {

    suspend fun getUserProfile(authHeader: String): Flow<UserProfileRepo>

    suspend fun getUserAnimeList(authHeader: String): Flow<UserAnimeListRepo>

    suspend fun updateUserAnimeStatus(authHeader: String, id: Int, numEpisodesWatched: Int, status: String, score: Int): Flow<UserAnimeUpdateRepo>

    suspend fun deleteUserAnimeStatus(authHeader: String, id: Int)
}