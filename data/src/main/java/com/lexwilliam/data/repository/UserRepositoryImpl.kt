package com.lexwilliam.data.repository

import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data.constant.ApiConstant
import com.lexwilliam.data.mapper.AnimeMapper
import com.lexwilliam.data.mapper.UserMapper
import com.lexwilliam.domain.model.remote.user.UserAnimeList
import com.lexwilliam.domain.model.remote.user.UserAnimeUpdate
import com.lexwilliam.domain.model.remote.user.UserProfile
import com.lexwilliam.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteSource: UserRemoteSource,
    private val oAuthLocalSource: OAuthLocalSource,
    private val animeMapper: AnimeMapper,
    private val userMapper: UserMapper
): UserRepository {

    override suspend fun getUserProfile(): Flow<UserProfile> {
        val accessToken = oAuthLocalSource.accessTokenFlow.firstOrNull()
        return userRemoteSource.getUserProfile(ApiConstant.BEARER_SEPARATOR + accessToken).map { userMapper.toDomain(it) }
    }

    override suspend fun getUserAnimeList(): Flow<UserAnimeList> {
        val accessToken = oAuthLocalSource.accessTokenFlow.firstOrNull()
        return userRemoteSource.getUserAnimeList(ApiConstant.BEARER_SEPARATOR + accessToken).map { animeMapper.toDomain(it) }
    }

    override suspend fun updateUserAnimeStatus(id: Int, numEpisodesWatched: Int, status: String, score: Int): Flow<UserAnimeUpdate> {
        val accessToken = oAuthLocalSource.accessTokenFlow.firstOrNull()
        return userRemoteSource.updateUserAnimeStatus(ApiConstant.BEARER_SEPARATOR + accessToken, id, numEpisodesWatched, status, score).map { userMapper.toDomain(it) }
    }

    override suspend fun deleteUserAnimeStatus(id: Int) {
        val accessToken = oAuthLocalSource.accessTokenFlow.firstOrNull()
        return userRemoteSource.deleteUserAnimeStatus(authHeader = ApiConstant.BEARER_SEPARATOR + accessToken, id)
    }

    override suspend fun continueAsGuest() {
        oAuthLocalSource.setAccessToken("GUEST")
    }


}