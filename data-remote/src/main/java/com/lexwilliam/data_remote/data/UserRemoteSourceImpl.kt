package com.lexwilliam.data_remote.data

import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data.model.remote.user.UserAnimeListRepo
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.AnimeMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRemoteSourceImpl @Inject constructor(
    private val malService: MyAnimeListService,
    private val animeMapper: AnimeMapper
): UserRemoteSource {

    override suspend fun getUserInfo(authHeader: String): String? =
        malService.getUserInfo(authHeader).body()?.name

    override suspend fun getUserAnimeList(authHeader: String): Flow<UserAnimeListRepo> = flow {
        val response = malService.getAnimeListOfUser(authHeader).body()
        emit(animeMapper.toRepo(response!!))
    }

}