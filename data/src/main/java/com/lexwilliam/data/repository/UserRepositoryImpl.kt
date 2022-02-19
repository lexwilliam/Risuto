package com.lexwilliam.data.repository

import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data.constant.ApiConstant
import com.lexwilliam.domain.repository.UserRepository
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteSource: UserRemoteSource
): UserRepository {
    override suspend fun getUserInfo(accessToken: String): String? {
        Timber.d("%s%s", ApiConstant.BEARER_SEPARATOR, accessToken)
        return userRemoteSource.getUserInfo(ApiConstant.BEARER_SEPARATOR + accessToken)
    }


}