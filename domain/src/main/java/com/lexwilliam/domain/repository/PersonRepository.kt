package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.remote.people.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun getPeopleById(id: Int): Flow<Person>
}