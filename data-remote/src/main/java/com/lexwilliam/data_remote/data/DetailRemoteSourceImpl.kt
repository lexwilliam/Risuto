package com.lexwilliam.data_remote.data

import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.model.remote.anime.AnimeDetailRepo
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.DetailMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRemoteSourceImpl @Inject constructor(
    private val malService: MyAnimeListService,
    private val detailMapper: DetailMapper
): DetailRemoteSource {
    override suspend fun getAnimeDetails(authHeader: String, id: Int): Flow<AnimeDetailRepo> = flow {
        val response = malService.getAnimeDetails(authHeader = authHeader, malId = id).body()!!
        emit(detailMapper.toRepo(response))
    }
}