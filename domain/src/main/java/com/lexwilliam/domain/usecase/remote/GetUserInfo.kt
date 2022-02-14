package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.model.remote.auth.AccessToken
import com.lexwilliam.domain.repository.OAuthRepository
import com.lexwilliam.domain.repository.UserRepository
import javax.inject.Inject

interface GetUserInfo {
    suspend fun execute(): String?
}

class GetUserInfoImpl @Inject constructor(
    private val userRepository: UserRepository
): GetUserInfo {
    override suspend fun execute(): String? {
        return userRepository.getUserInfo()
    }
}