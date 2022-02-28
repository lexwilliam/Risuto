package com.lexwilliam.data_remote.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.model.remote.anime.AnimeRepo
import com.lexwilliam.data_remote.JikanV4Service
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.lexwilliam.data_remote.paging.SearchPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeRemoteSourceImpl @Inject constructor(
    private val jikanV4Service: JikanV4Service,
    private val animeMapper: AnimeMapper
): AnimeRemoteSource {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
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
        page: Int?,
        limit: Int?,
        q: String?,
        type: String?,
        score: Double?,
        minScore: Double?,
        maxScore: Double?,
        status: String?,
        rating: String?,
        sfw: Boolean?,
        genres: String?,
        genresExclude: String?,
        orderBy: String?,
        sort: String?,
        letter: String?,
        producer: String?
    ): Flow<AnimeRepo> = flow {
        val response = jikanV4Service.getSearchAnime(page, limit, q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer)
        emit(animeMapper.toRepo(response))
    }

    override fun getSearchAnimePaging(
        q: String?,
        type: String?,
        score: Double?,
        minScore: Double?,
        maxScore: Double?,
        status: String?,
        rating: String?,
        sfw: Boolean?,
        genres: String?,
        genresExclude: String?,
        orderBy: String?,
        sort: String?,
        letter: String?,
        producer: String?
    ): Flow<PagingData<AnimeRepo.Data>> {
        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = { SearchPagingSource(jikanV4Service, q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer) }
        ).flow.map { it.map { animeMapper.toRepo(it) } }
    }

    override suspend fun getAnimeById(id: Int): Flow<AnimeRepo.Data> = flow {
        val response = jikanV4Service.getAnimeById(id)
        emit(animeMapper.toRepo(response))
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

}