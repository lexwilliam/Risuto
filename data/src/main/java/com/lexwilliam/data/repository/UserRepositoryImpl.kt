package com.lexwilliam.data.repository

import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data.constant.ApiConstant
import com.lexwilliam.domain.repository.UserRepository
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteSource: UserRemoteSource,
    private val oAuthLocalSource: OAuthLocalSource
): UserRepository {
    override suspend fun getUserInfo(): String? {
        var accessToken = ""
        oAuthLocalSource.accessTokenFlow.collect {
            accessToken = it?:""
        }
        Timber.d("accessToken : $accessToken")
        return if (accessToken != "") {
            userRemoteSource.getUserInfo(ApiConstant.BEARER_SEPARATOR + accessToken)
        } else {
            Timber.d("accessToken not found")
            "ERROR"
        }

    }


}