package com.lexwilliam.data_local.data

import com.lexwilliam.data.MyAnimeLocalSource
import com.lexwilliam.data.model.local.MyAnimeRepo
import com.lexwilliam.data.model.local.WatchStatusRepo
import com.lexwilliam.data_local.dao.MyAnimeDao
import com.lexwilliam.data_local.mapper.MyAnimeMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MyAnimeLocalSourceImpl @Inject constructor(
    private val myAnimeDao: MyAnimeDao,
    private val myAnimeMapper: MyAnimeMapper
): MyAnimeLocalSource {

    override fun getMyAnimes(): Flow<List<MyAnimeRepo>> {
        return myAnimeDao.getMyAnimes().map { anime -> anime.map { myAnimeMapper.toRepo(it) } }
    }

    override fun getMyAnimeWithWatchStatus(watchStatus: WatchStatusRepo): Flow<List<MyAnimeRepo>> {
        return myAnimeDao.getMyAnimeWithWatchStatus(myAnimeMapper.toEntity(watchStatus)).map { anime -> anime.map { myAnimeMapper.toRepo(it) } }
    }

    override suspend fun deleteMyAnime(myAnime: MyAnimeRepo): Int {
        return myAnimeDao.deleteMyAnime(myAnimeMapper.toEntity(myAnime))
    }

    override suspend fun updateMyAnime(myAnime: MyAnimeRepo): Int {
        return myAnimeDao.updateMyAnime(myAnimeMapper.toEntity(myAnime))
    }

    override suspend fun insertMyAnime(myAnime: MyAnimeRepo): Long {
        return myAnimeDao.insertMyAnime(myAnimeMapper.toEntity(myAnime))
    }


}