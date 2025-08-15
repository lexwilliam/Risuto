package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.character.CharacterDetail
import com.lexwilliam.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCharacterById {
    suspend fun execute(id: Int): Flow<CharacterDetail>
}

class GetCharacterByIdImpl @Inject constructor(
    private val characterRepository: CharacterRepository
): GetCharacterById {
    override suspend fun execute(id: Int): Flow<CharacterDetail> {
        return characterRepository.getCharacterById(id)
    }
}