package com.lexwilliam.data_remote.data

import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.model.remote.search.SearchRepo
import com.lexwilliam.data.model.remote.season.SeasonRepo
import com.lexwilliam.data.model.remote.top.TopRepo
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.lexwilliam.data_remote.mapper.CommonMapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AnimeRemoteSourceImpl @Inject constructor(
    private val jikanService: JikanService,
    private val animeMapper: AnimeMapper
): AnimeRemoteSource {

    override suspend fun searchAnime(
        q: String?,
        type: String?,
        status: String?,
        genre: Int?,
        limit: Int?,
        orderBy: String?,
        sort: String?,
        page: Int?
    ): Flow<SearchRepo> = flow {
        val searchResponse = jikanService.getSearchAnimeResult(
            q, type, status, genre, limit, orderBy, sort, page
        )
        emit(animeMapper.toRepo(searchResponse))
    }

    override suspend fun genreAnime(
        q: String?,
        type: String?,
        status: String?,
        genre: Int?,
        limit: Int?,
        orderBy: String?,
        sort: String?,
        page: Int?
    ): Flow<SearchRepo> = flow {
        val searchResponse = jikanService.getSearchAnimeResult(
            q, type, status, genre, limit, orderBy, sort, page
        )
        emit(animeMapper.toRepo(searchResponse))
    }

    override suspend fun topAnime(page: Int, subType: String): Flow<TopRepo> = flow {
        val topResponse = jikanService.getTopResult(page, subType)
        emit(animeMapper.toRepo(topResponse))
    }

    override suspend fun currentSeasonAnime(): Flow<SeasonRepo> = flow {
        val seasonResponse = jikanService.getCurrentSeasonAnimeResult()
        emit(animeMapper.toRepo(seasonResponse))
    }

    override suspend fun seasonAnime(year: Int?, season: String?): Flow<SeasonRepo> = flow {
        val seasonResponse = jikanService.getSeasonAnimeResult(year, season)
        emit(animeMapper.toRepo(seasonResponse))
    }

}