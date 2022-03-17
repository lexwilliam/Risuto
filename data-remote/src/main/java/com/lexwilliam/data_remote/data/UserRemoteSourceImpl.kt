package com.lexwilliam.data_remote.data

import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data.model.remote.user.UserAnimeListRepo
import com.lexwilliam.data.model.remote.user.UserAnimeUpdateRepo
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.lexwilliam.data_remote.mapper.UserMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRemoteSourceImpl @Inject constructor(
    private val malService: MyAnimeListService,
    private val animeMapper: AnimeMapper,
    private val userMapper: UserMapper
): UserRemoteSource {

    override suspend fun getUserInfo(authHeader: String): String? =
        malService.getUserInfo(authHeader).body()?.name

    override suspend fun getUserAnimeList(authHeader: String): Flow<UserAnimeListRepo> = flow {
        val response = malService.getUserAnimeList(authHeader).body()
        emit(animeMapper.toRepo(response!!))
    }

    override suspend fun updateUserAnimeStatus(authHeader: String, id: Int, numEpisodesWatched: Int, status: String, score: Int): Flow<UserAnimeUpdateRepo> = flow {
        val response = malService.updateUserAnimeStatus(authHeader = authHeader, animeId = id, numWatchedEps = numEpisodesWatched, animeStatus = status, score = score).body()
        emit(userMapper.toRepo(response!!))
    }

    override suspend fun deleteUserAnimeStatus(authHeader: String, id: Int) {
        malService.deleteUserAnimeStatus(authHeader, id)
    }


}