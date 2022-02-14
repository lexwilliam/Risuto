package com.lexwilliam.data_remote.data

import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data_remote.MyAnimeListService
import retrofit2.http.Header
import javax.inject.Inject

class UserRemoteSourceImpl @Inject constructor(
    private val malService: MyAnimeListService
): UserRemoteSource {

    override suspend fun getUserInfo(authHeader: String): String? =
        malService.getUserInfo(authHeader).body()?.name

}