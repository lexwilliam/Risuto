package com.lexwilliam.data_remote.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.model.remote.anime.AnimeRepo
import com.lexwilliam.data.model.remote.anime.SeasonListRepo
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.lexwilliam.data_remote.paging.SearchPagingSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AnimeRemoteSourceImpl @Inject constructor(
    private val jikanService: JikanService,
    private val animeMapper: AnimeMapper
): AnimeRemoteSource {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
    }

    private val _topAnimeSharedFlow = MutableStateFlow(getInitialStateAnime())
    private val topAnimeSharedFlow = _topAnimeSharedFlow.asSharedFlow()
    private val _seasonNowSharedFlow = MutableStateFlow(getInitialStateAnime())
    private val seasonNowSharedFlow = _seasonNowSharedFlow.asSharedFlow()
    private val _seasonSharedFlow = MutableStateFlow(getInitialStateAnime())
    private val seasonSharedFlow = _seasonSharedFlow.asSharedFlow()
    private val _seasonListSharedFlow = MutableStateFlow(getInitialStateSeasonList())
    private val seasonListSharedFlow = _seasonListSharedFlow.asSharedFlow()
    private val _schedulesSharedFlow = MutableStateFlow(getInitialStateAnime())
    private val schedulesSharedFlow = _schedulesSharedFlow.asSharedFlow()
    private val _searchSharedFlow = MutableStateFlow(getInitialStateAnime())
    private val searchSharedFlow = _searchSharedFlow.asSharedFlow()

    override suspend fun getTopAnime(): Flow<AnimeRepo> {
        try {
            animeMapper.toRepo(jikanService.getTopAnime())
                .let {
                    _topAnimeSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return topAnimeSharedFlow.distinctUntilChanged()
    }

    override suspend fun getSeasonNow(): Flow<AnimeRepo> {
        try {
            animeMapper.toRepo(jikanService.getSeasonNow())
                .let {
                    _seasonNowSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return seasonNowSharedFlow.distinctUntilChanged()
    }

    override suspend fun getSeason(year: Int, season: String): Flow<AnimeRepo> {
        try {
            animeMapper.toRepo(jikanService.getSeason(year, season))
                .let {
                    _seasonSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return seasonSharedFlow.distinctUntilChanged()
    }

    override suspend fun getSeasonList(): Flow<SeasonListRepo> {
        try {
            animeMapper.toRepo(jikanService.getSeasonList())
                .let {
                    _seasonListSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return seasonListSharedFlow.distinctUntilChanged()
    }

    override suspend fun getSchedules(dayOfWeek: String): Flow<AnimeRepo> {
        try {
            animeMapper.toRepo(jikanService.getSchedules(dayOfWeek))
                .let {
                    _schedulesSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return schedulesSharedFlow.distinctUntilChanged()
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
    ): Flow<AnimeRepo>{
        try {
            animeMapper.toRepo(jikanService.getSearchAnime(page, limit, q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer))
                .let {
                    _searchSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return searchSharedFlow.distinctUntilChanged()
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
            pagingSourceFactory = { SearchPagingSource(jikanService, q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer) }
        ).flow.map { it.map { animeMapper.toRepo(it) } }
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

    private fun getInitialStateAnime() = AnimeRepo(emptyList(), AnimeRepo.Pagination(false, -1))

    private fun getInitialStateSeasonList() = SeasonListRepo(emptyList())

}