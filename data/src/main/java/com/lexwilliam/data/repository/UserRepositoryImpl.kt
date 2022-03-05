package com.lexwilliam.data.repository

import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data.constant.ApiConstant
import com.lexwilliam.data.mapper.AnimeMapper
import com.lexwilliam.domain.model.remote.user.UserAnimeList
import com.lexwilliam.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteSource: UserRemoteSource,
    private val oAuthLocalSource: OAuthLocalSource,
    private val animeMapper: AnimeMapper
): UserRepository {

    override suspend fun getUserInfo(): String? {
        val accessToken = oAuthLocalSource.accessTokenFlow.firstOrNull()
        return userRemoteSource.getUserInfo(ApiConstant.BEARER_SEPARATOR + accessToken)
    }

    override suspend fun getUserAnimeList(): Flow<UserAnimeList> {
        val accessToken = oAuthLocalSource.accessTokenFlow.firstOrNull()
        return userRemoteSource.getUserAnimeList(ApiConstant.BEARER_SEPARATOR + accessToken).map { animeMapper.toDomain(it) }
    }


}