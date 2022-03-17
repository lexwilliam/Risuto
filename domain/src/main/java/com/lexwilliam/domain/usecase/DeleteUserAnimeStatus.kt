package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.repository.HistoryRepository
import com.lexwilliam.domain.repository.UserRepository
import javax.inject.Inject

interface DeleteUserAnimeStatus {
    suspend fun execute(id: Int)
}

class DeleteUserAnimeStatusImpl @Inject constructor(
    private val userRepository: UserRepository
): DeleteUserAnimeStatus {
    override suspend fun execute(id: Int) {
        return userRepository.deleteUserAnimeStatus(id)
    }
}