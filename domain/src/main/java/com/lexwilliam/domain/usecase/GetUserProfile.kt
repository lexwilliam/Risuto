package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.user.UserProfile
import com.lexwilliam.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetUserProfile {
    suspend fun execute(): Flow<UserProfile>
}

class GetUserProfileImpl @Inject constructor(
    private val userRepository: UserRepository
): GetUserProfile {
    override suspend fun execute(): Flow<UserProfile> {
        return userRepository.getUserProfile()
    }
}