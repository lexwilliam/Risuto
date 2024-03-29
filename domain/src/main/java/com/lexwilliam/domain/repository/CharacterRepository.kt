package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.remote.character.CharacterDetail
import com.lexwilliam.domain.model.remote.people.Person
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacterById(id: Int): Flow<CharacterDetail>
}