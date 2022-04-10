package com.lexwilliam.data_remote.data

import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.model.remote.anime.AnimeCharactersRepo
import com.lexwilliam.data.model.remote.anime.AnimeDetailRepo
import com.lexwilliam.data.model.remote.anime.AnimeStaffRepo
import com.lexwilliam.data.model.remote.anime.AnimeVideosRepo
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.DetailMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class DetailRemoteSourceImpl @Inject constructor(
    private val malService: MyAnimeListService,
    private val jikanService: JikanService,
    private val detailMapper: DetailMapper
): DetailRemoteSource {
    override suspend fun getAnimeDetails(authHeader: String, id: Int): Flow<AnimeDetailRepo> = flow {
        val response = malService.getAnimeDetails(authHeader = authHeader, malId = id).body()!!
        emit(detailMapper.toRepo(response))
    }

    override suspend fun getAnimeCharacters(id: Int): Flow<AnimeCharactersRepo> = flow {
        val response = jikanService.getAnimeCharacters(id)
        emit(detailMapper.toRepo(response))
    }

    override suspend fun getAnimeVideos(id: Int): Flow<AnimeVideosRepo> = flow {
        val response = jikanService.getAnimeVideos(id)
        emit(detailMapper.toRepo(response))
    }

    override suspend fun getAnimeStaff(id: Int): Flow<AnimeStaffRepo> = flow {
        val response = jikanService.getAnimeStaff(id)
        emit(detailMapper.toRepo(response))
    }
}