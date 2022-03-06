package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.anime.AnimeDetail
import com.lexwilliam.domain.model.remote.user.UserAnimeUpdate
import com.lexwilliam.domain.repository.DetailRepository
import com.lexwilliam.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UpdateUserAnimeStatus {
    suspend fun execute(id: Int, status: String, score: Int): Flow<UserAnimeUpdate>
}

class UpdateUserAnimeStatusImpl @Inject constructor(
    private val userRepository: UserRepository
): UpdateUserAnimeStatus {
    override suspend fun execute(id: Int, status: String, score: Int): Flow<UserAnimeUpdate> {
        return userRepository.updateUserAnimeStatus(id, status, score)
    }
}