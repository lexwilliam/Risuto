package com.lexwilliam.data.repository

import com.lexwilliam.data.MyAnimeLocalSource
import com.lexwilliam.data.mapper.MyAnimeMapper
import com.lexwilliam.data.model.local.MyAnimeRepo
import com.lexwilliam.data.model.local.WatchStatusRepo
import com.lexwilliam.domain.model.local.MyAnime
import com.lexwilliam.domain.model.local.WatchStatus
import com.lexwilliam.domain.repository.MyAnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MyAnimeRepositoryImpl
@Inject constructor(
    private val myAnimeLocalSource: MyAnimeLocalSource,
    private val myAnimeMapper: MyAnimeMapper
): MyAnimeRepository {
    override fun getMyAnimes(): Flow<List<MyAnime>> {
        return myAnimeLocalSource.getMyAnimes().map { anime -> anime.map { myAnimeMapper.toDomain(it) } }
    }

    override fun getMyAnimeWithWatchStatus(watchStatus: WatchStatus): Flow<List<MyAnime>> {
        return myAnimeLocalSource.getMyAnimeWithWatchStatus(myAnimeMapper.toRepo(watchStatus)).map { anime -> anime.map { myAnimeMapper.toDomain(it) } }
    }

    override suspend fun deleteMyAnime(myAnime: MyAnime): Int {
        return myAnimeLocalSource.deleteMyAnime(myAnimeMapper.toRepo(myAnime))
    }

    override suspend fun updateMyAnime(myAnime: MyAnime): Int {
        return myAnimeLocalSource.updateMyAnime(myAnimeMapper.toRepo(myAnime))
    }

    override suspend fun insertMyAnime(myAnime: MyAnime): Long {
        return myAnimeLocalSource.insertMyAnime(myAnimeMapper.toRepo(myAnime))
    }

}