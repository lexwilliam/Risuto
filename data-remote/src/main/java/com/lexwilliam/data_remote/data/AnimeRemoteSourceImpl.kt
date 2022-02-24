package com.lexwilliam.data_remote.data

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.Pager
import androidx.paging.map
import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.model.remote.anime.AnimeRepo
import com.lexwilliam.data.model.remote.search.SearchAnimeRepo
import com.lexwilliam.data.model.remote.search.SearchRepo
import com.lexwilliam.data.model.remote.season.SeasonRepo
import com.lexwilliam.data.model.remote.top.TopRepo
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.JikanV4Service
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.lexwilliam.data_remote.paging.SearchPagingSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AnimeRemoteSourceImpl @Inject constructor(
    private val jikanService: JikanService,
    private val jikanV4Service: JikanV4Service,
    private val animeMapper: AnimeMapper
): AnimeRemoteSource {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
    }

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

    override fun genreAnime(
        q: String?,
        type: String?,
        status: String?,
        genre: Int?,
        orderBy: String?,
        sort: String?
    ): Flow<PagingData<SearchAnimeRepo>> {
        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = { SearchPagingSource(jikanService, q, type, status, genre, orderBy, sort) }
        ).flow.map { it.map { animeMapper.toRepo(it) } }
    }

    override suspend fun topAnime(page: Int, subType: String): Flow<TopRepo> = flow {
        val topResponse = jikanService.getTopResult(page, subType)
        emit(animeMapper.toRepo(topResponse))
    }

    override suspend fun getTopAnime(): Flow<AnimeRepo> = flow {
        val response = jikanV4Service.getTopAnime()
        emit(animeMapper.toRepo(response))
    }

    override suspend fun getSeasonNow(): Flow<AnimeRepo> = flow {
        val response = jikanV4Service.getSeasonNow()
        emit(animeMapper.toRepo(response))
    }

    override suspend fun getSeason(year: Int, season: String): Flow<AnimeRepo> = flow {
        val response = jikanV4Service.getSeason(year, season)
        emit(animeMapper.toRepo(response))
    }

    override suspend fun getSchedules(dayOfWeek: String): Flow<AnimeRepo> = flow {
        val response = jikanV4Service.getSchedules(dayOfWeek)
        emit(animeMapper.toRepo(response))
    }

    override suspend fun getSearchAnime(
        page: Int,
        limit: Int,
        q: String,
        type: String,
        score: Double,
        minScore: Double,
        maxScore: Double,
        status: String,
        rating: String,
        sfw: Boolean,
        genres: String,
        genresExclude: String,
        orderBy: String,
        sort: String,
        letter: String,
        producer: String
    ): Flow<AnimeRepo> = flow {
        val response = jikanV4Service.getSearchAnime(page, limit, q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer)
        emit(animeMapper.toRepo(response))
    }

    override suspend fun currentSeasonAnime(): Flow<SeasonRepo> = flow {
        val seasonResponse = jikanService.getCurrentSeasonAnimeResult()
        emit(animeMapper.toRepo(seasonResponse))
    }

    override suspend fun seasonAnime(year: Int?, season: String?): Flow<SeasonRepo> = flow {
        val seasonResponse = jikanService.getSeasonAnimeResult(year, season)
        emit(animeMapper.toRepo(seasonResponse))
    }

    override suspend fun getAnimeById(id: Int): Flow<AnimeRepo.Data> = flow {
        val response = jikanV4Service.getAnimeById(id)
        emit(animeMapper.toRepo(response))
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

}