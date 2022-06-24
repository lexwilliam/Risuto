package com.lexwilliam.data

import com.lexwilliam.data.model.remote.people.PersonRepo
import kotlinx.coroutines.flow.Flow

interface PersonRemoteSource {
    suspend fun getPeopleById(id: Int): Flow<PersonRepo>
}