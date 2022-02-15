package com.lexwilliam.data.repository

import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data.constant.ApiConstant
import com.lexwilliam.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteSource: UserRemoteSource
): UserRepository {
    override suspend fun getUserInfo(accessToken: String): String? {
        return userRemoteSource.getUserInfo(ApiConstant.BEARER_SEPARATOR + accessToken)
    }


}