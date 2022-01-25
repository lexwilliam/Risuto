package com.lexwilliam.data

import com.example.risuto.data.local.dao.MyAnimeDao
import com.example.risuto.data.local.mapper.toDomain
import com.example.risuto.data.local.mapper.toEntity
import com.example.risuto.data.local.model.WatchStatus
import com.lexwilliam.domain.model.MyAnime
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

    override suspend fun getMyAnimeWithWatchStatus(watchStatus: WatchStatus): Flow<List<MyAnime>> = flow{
        val animes = myAnimeDao.getMyAnimeWithWatchStatus(watchStatus)
        emit(animes.map {it.toDomain()})
    }

    override suspend fun deleteMyAnime(myAnime: MyAnime): Flow<Int> = flow {
        val affectedAnime = myAnimeDao.deleteMyAnime(myAnime.toEntity())
        emit(affectedAnime)
    }

    override suspend fun insert(myAnime: MyAnime): Flow<Long> = flow {
        val animes = myAnimeDao.getMyAnimes()
        if(animes.contains(myAnime.toEntity())) {
            val result = myAnimeDao.updateMyAnime(myAnime.toEntity())
            emit(-1)
        } else {
            val result = myAnimeDao.insert(myAnime.toEntity())
            emit(result)
        }
    }
}