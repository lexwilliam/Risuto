package com.example.risuto.data.local.repository

import com.example.risuto.data.local.Results
import com.example.risuto.data.local.dao.MyAnimeDao
import com.example.risuto.data.local.mapper.toDomain
import com.example.risuto.domain.model.MyAnime
import com.example.risuto.domain.repository.IMyAnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MyAnimeRepository
@Inject constructor(
    private val myAnimeDao: MyAnimeDao
): IMyAnimeRepository {
    override suspend fun getMyAnimes(): Flow<List<MyAnime>> = flow {
        val animes = myAnimeDao.getMyAnimes()
        emit(animes.map {it.toDomain()})
    }

    override suspend fun deleteMyAnime(malId: Int): Flow<Int> = flow {
        val affectedAnime = myAnimeDao.deleteMyAnime(malId)
        emit(affectedAnime)
    }

    override suspend fun insert(myAnime: MyAnime): Flow<Results> = flow {
        val result = myAnimeDao.insert(myAnime)
        emit(result)
    }
}