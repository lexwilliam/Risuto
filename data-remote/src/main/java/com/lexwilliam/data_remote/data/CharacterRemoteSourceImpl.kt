package com.lexwilliam.data_remote.data

import com.lexwilliam.data.CharacterRemoteSource
import com.lexwilliam.data.model.remote.character.CharacterDetailRepo
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.mapper.CharacterMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged

class CharacterRemoteSourceImpl(
    private val jikanService: JikanService,
    private val characterMapper: CharacterMapper
): CharacterRemoteSource {

    private val _characterSharedFlow = MutableStateFlow(getInitialStateCharacter())
    private val characterSharedFlow = _characterSharedFlow.asSharedFlow()

    override suspend fun getCharacterById(id: Int): Flow<CharacterDetailRepo> {
        try {
            characterMapper.toRepo(jikanService.getCharacterById(id))
                .let  {
                    _characterSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return characterSharedFlow.distinctUntilChanged()
    }

    private fun getInitialStateCharacter() =
        CharacterDetailRepo(
            CharacterDetailRepo.Data(
                "", emptyList(), -1, -1, ""
                , "", "", emptyList()
            )
        )
}