package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.people.Person
import com.lexwilliam.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPeopleById {
    suspend fun execute(id: Int): Flow<Person>
}

class GetPeopleByIdImpl @Inject constructor(
    private val personRepository: PersonRepository
): GetPeopleById {
    override suspend fun execute(id: Int): Flow<Person> {
        return personRepository.getPeopleById(id)
    }
}