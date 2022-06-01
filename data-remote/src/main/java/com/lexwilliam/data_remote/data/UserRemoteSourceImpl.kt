package com.lexwilliam.data_remote.data

import android.media.Image
import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data.model.remote.user.UserAnimeListRepo
import com.lexwilliam.data.model.remote.user.UserAnimeUpdateRepo
import com.lexwilliam.data.model.remote.user.UserProfileRepo
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.lexwilliam.data_remote.mapper.UserMapper
import com.lexwilliam.data_remote.model.user.UserProfileResponse
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserRemoteSourceImpl @Inject constructor(
    private val malService: MyAnimeListService,
    private val jikanService: JikanService,
    private val animeMapper: AnimeMapper,
    private val userMapper: UserMapper
): UserRemoteSource {

    private val _profileSharedFlow = MutableStateFlow(getInitialStateUserProfile())
    private val profileSharedFlow = _profileSharedFlow.asSharedFlow()
    private val _userAnimeSharedFlow = MutableStateFlow(getInitialStateUserAnime())
    private val userAnimeSharedFlow = _userAnimeSharedFlow.asSharedFlow()

    override suspend fun getUserProfile(authHeader: String): Flow<UserProfileRepo> {
        try {
            val username = malService.getUserInfo(authHeader).body()?.name
            if(username != null) {
                userMapper.toRepo(jikanService.getUserProfile(username))
                    .let {
                        _profileSharedFlow.emit(it)
                    }
            }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return profileSharedFlow.distinctUntilChanged()
    }

    override suspend fun getUserAnimeList(authHeader: String): Flow<UserAnimeListRepo> {
        try {
            animeMapper.toRepo(malService.getUserAnimeList(authHeader).body()!!)
                .let {
                    _userAnimeSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return userAnimeSharedFlow.distinctUntilChanged()
    }

    override suspend fun updateUserAnimeStatus(authHeader: String, id: Int, numEpisodesWatched: Int, status: String, score: Int): Flow<UserAnimeUpdateRepo> = flow {
        val response = malService.updateUserAnimeStatus(authHeader = authHeader, animeId = id, numWatchedEps = numEpisodesWatched, animeStatus = status, score = score).body()
        emit(userMapper.toRepo(response!!))
    }

    override suspend fun deleteUserAnimeStatus(authHeader: String, id: Int) {
        malService.deleteUserAnimeStatus(authHeader, id)
    }

    private fun getInitialStateUserProfile() =
        UserProfileRepo(UserProfileRepo.Data("", "", UserProfileRepo.Data.Images(
            UserProfileRepo.Data.Images.Jpg(""), UserProfileRepo.Data.Images.Webp("")),
            "", "", "", -1, "", ""))

    private fun getInitialStateUserAnime() =
        UserAnimeListRepo(emptyList())

}