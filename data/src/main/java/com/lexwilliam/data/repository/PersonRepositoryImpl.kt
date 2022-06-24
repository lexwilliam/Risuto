package com.lexwilliam.data.repository

import com.lexwilliam.data.PersonRemoteSource
import com.lexwilliam.data.mapper.PersonMapper
import com.lexwilliam.domain.model.remote.people.Person
import com.lexwilliam.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personRemoteSource: PersonRemoteSource,
    private val personMapper: PersonMapper
): PersonRepository {
    override suspend fun getPeopleById(id: Int): Flow<Person> {
        return personRemoteSource.getPeopleById(id).map { personMapper.toDomain(it) }
    }
}