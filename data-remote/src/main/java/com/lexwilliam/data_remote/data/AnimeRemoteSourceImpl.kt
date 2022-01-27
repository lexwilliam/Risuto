package com.lexwilliam.data_remote.data

import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.model.remote.search.SearchRepo
import com.lexwilliam.data.model.remote.season.SeasonRepo
import com.lexwilliam.data.model.remote.top.TopRepo
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.lexwilliam.data_remote.mapper.CommonMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class AnimeRemoteSourceImpl @Inject constructor(
    private val jikanService: JikanService,
    private val animeMapper: AnimeMapper
): AnimeRemoteSource {

    private val _searchAnimeSharedFlow = MutableStateFlow(getInitialStateSearchAnime())
    private val searchAnimeSharedFlow = _searchAnimeSharedFlow.asSharedFlow()
    private val _topAnimeSharedFlow = MutableStateFlow(getInitialStateTopAnime())
    private val topAnimeSharedFlow = _topAnimeSharedFlow.asSharedFlow()
    private val _seasonAnimeSharedFlow = MutableStateFlow(getInitialStateSeasonAnime())
    private val seasonAnimeSharedFlow = _seasonAnimeSharedFlow.asSharedFlow()
    private val _genreAnimeSharedFlow = MutableStateFlow(getInitialStateSearchAnime())
    private val genreAnimeSharedFlow = _genreAnimeSharedFlow.asSharedFlow()

    override suspend fun searchAnime(
        q: String?,
        type: String?,
        status: String?,
        genre: Int?,
        limit: Int?,
        orderBy: String?,
        sort: String?,
        page: Int?
    ): Flow<SearchRepo> {
        try {
            animeMapper.toRepo(jikanService.getSearchAnimeResult(q, type, status, genre, limit, orderBy, sort, page))
                .let { searchRepo ->
                    _searchAnimeSharedFlow.emit(searchRepo)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return searchAnimeSharedFlow.distinctUntilChanged()
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
    ): Flow<SearchRepo> {
        try {
            animeMapper.toRepo(jikanService.getSearchAnimeResult(q, type, status, genre, limit, orderBy, sort, page))
                .let { searchRepo ->
                    _genreAnimeSharedFlow.emit(searchRepo)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return genreAnimeSharedFlow.distinctUntilChanged()
    }

    override suspend fun topAnime(page: Int, subType: String): Flow<TopRepo> {
        try {
            animeMapper.toRepo(jikanService.getTopResult(page, subType))
                .let { topRepo ->
                    _topAnimeSharedFlow.emit(topRepo)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return topAnimeSharedFlow.distinctUntilChanged()
    }

    override suspend fun seasonAnime(year: Int?, season: String?): Flow<SeasonRepo> {
        try {
            animeMapper.toRepo(jikanService.getSeasonAnimeResult(year, season))
                .let { seasonRepo ->
                    _seasonAnimeSharedFlow.emit(seasonRepo)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return seasonAnimeSharedFlow.distinctUntilChanged()
    }

    private fun getInitialStateSearchAnime() =
        SearchRepo("", false, -1, emptyList(), -1)

    private fun getInitialStateTopAnime() =
        TopRepo("", false, -1, emptyList())

    private fun getInitialStateSeasonAnime() =
        SeasonRepo("", false, -1, "", -1, emptyList())


}