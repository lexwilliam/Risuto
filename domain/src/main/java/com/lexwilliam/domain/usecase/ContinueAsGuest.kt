package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.repository.UserRepository
import javax.inject.Inject

interface ContinueAsGuest {
    suspend fun execute()
}

class ContinueAsGuestImpl @Inject constructor(
    private val userRepository: UserRepository
): ContinueAsGuest {
    override suspend fun execute() {
        return userRepository.continueAsGuest()
    }
}