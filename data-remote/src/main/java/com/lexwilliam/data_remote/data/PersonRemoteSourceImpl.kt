package com.lexwilliam.data_remote.data

import com.lexwilliam.data.PersonRemoteSource
import com.lexwilliam.data.model.remote.people.PersonRepo
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.mapper.PersonMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class PersonRemoteSourceImpl @Inject constructor(
    private val jikanService: JikanService,
    private val personMapper: PersonMapper
): PersonRemoteSource {

    private val _personSharedFlow = MutableStateFlow(getInitialStatePerson())
    private val personSharedFlow = _personSharedFlow.asSharedFlow()

    override suspend fun getPeopleById(id: Int): Flow<PersonRepo> {
        try {
            personMapper.toRepo(jikanService.getPersonFullById(id))
                .let  {
                    _personSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return personSharedFlow.distinctUntilChanged()
    }

    private fun getInitialStatePerson() =
        PersonRepo(
            PersonRepo.Data(
            "", emptyList(), emptyList(), "", "",
                -1, "", PersonRepo.Data.Images(
                    PersonRepo.Data.Images.Jpg("")),
                -1, emptyList(), "", "", emptyList(), ""
        ))
}