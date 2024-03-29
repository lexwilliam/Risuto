package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.user.UserAnimeList
import com.lexwilliam.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetUserAnimeList {
    suspend fun execute(): Flow<UserAnimeList>
}

class GetUserAnimeListImpl @Inject constructor(
    private val userRepository: UserRepository
): GetUserAnimeList {
    override suspend fun execute(): Flow<UserAnimeList> {
        return userRepository.getUserAnimeList()
    }
}