package com.lexwilliam.data

import com.lexwilliam.data.model.remote.character.CharacterDetailRepo
import kotlinx.coroutines.flow.Flow


interface CharacterRemoteSource {
    suspend fun getCharacterById(id: Int): Flow<CharacterDetailRepo>
}