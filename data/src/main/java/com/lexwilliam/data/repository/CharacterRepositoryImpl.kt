package com.lexwilliam.data.repository

import com.lexwilliam.data.CharacterRemoteSource
import com.lexwilliam.data.mapper.CharacterMapper
import com.lexwilliam.domain.model.remote.character.CharacterDetail
import com.lexwilliam.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteSource: CharacterRemoteSource,
    private val characterMapper: CharacterMapper
): CharacterRepository {
    override suspend fun getCharacterById(id: Int): Flow<CharacterDetail> {
        return characterRemoteSource.getCharacterById(id).map { characterMapper.toDomain(it) }
    }
}