package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.auth.AccessToken
import com.lexwilliam.domain.repository.OAuthRepository
import com.lexwilliam.domain.repository.UserRepository
import javax.inject.Inject

interface GetUserInfo {
    suspend fun execute(accessToken: String): String?
}

class GetUserInfoImpl @Inject constructor(
    private val userRepository: UserRepository
): GetUserInfo {
    override suspend fun execute(accessToken: String): String? {
        return userRepository.getUserInfo(accessToken)
    }
}