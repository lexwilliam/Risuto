package com.lexwilliam.data_remote

import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.model.common.*
import com.lexwilliam.data.model.detail.AnimeRepo
import com.lexwilliam.data.model.search.SearchRepo
import com.lexwilliam.data.model.season.SeasonAnimeRepo
import com.lexwilliam.data.model.season.SeasonRepo
import com.lexwilliam.data.model.top.TopRepo
import com.lexwilliam.data_remote.api.JikanService
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.lexwilliam.data_remote.mapper.CommonMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class AnimeRemoteSourceImpl @Inject constructor(
    private val jikanService: JikanService,
    private val animeMapper: AnimeMapper,
    private val commonMapper: CommonMapper
): AnimeRemoteSource {

    private val _searchAnimeSharedFlow = MutableStateFlow(getInitialStateSearchAnime())
    private val searchAnimeSharedFlow = _searchAnimeSharedFlow.asSharedFlow()
    private val _topAnimeSharedFlow = MutableStateFlow(getInitialStateTopAnime())
    private val topAnimeSharedFlow = _topAnimeSharedFlow.asSharedFlow()
    private val _seasonAnimeSharedFlow = MutableStateFlow(getInitialStateSeasonAnime())
    private val seasonAnimeSharedFlow = _seasonAnimeSharedFlow.asSharedFlow()
    private val _animeDetailSharedFlow = MutableStateFlow(getInitialStateAnimeDetail())
    private val animeDetailSharedFlow = _animeDetailSharedFlow.asSharedFlow()

    override suspend fun getSearchAnime(
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

    override suspend fun getTopAnime(page: Int, subType: String): Flow<TopRepo> {
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

    override suspend fun getSeasonAnime(year: Int?, season: String?): Flow<SeasonRepo> {
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

    override suspend fun getAnimeDetail(id: Int): Flow<AnimeRepo> {
        try {
            animeMapper.toRepo(jikanService.getAnimeResult(id))
                .let { animeRepo ->
                    _animeDetailSharedFlow.emit(animeRepo)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return animeDetailSharedFlow.distinctUntilChanged()
    }

    private fun getInitialStateSearchAnime() =
        SearchRepo("", false, -1, emptyList(), -1)

    private fun getInitialStateTopAnime() =
        TopRepo("", false, -1, emptyList())

    private fun getInitialStateSeasonAnime() =
        SeasonRepo("", false, -1, "", -1, emptyList())

    private fun getInitialStateAnimeDetail() =
        AnimeRepo(
            AiredRepo("", PropRepo(FromRepo(-1,-1,-1), ToRepo(-1,-1,-1)),"", ""),
            false, "", "", "", listOf(""), -1,
            -1, listOf(GenreRepo(-1,"", "", "")), "",
            listOf(LicensorRepo(-1, "", "", "")), -1,-1,
            listOf(""), -1, "", listOf(ProducerRepo(-1, "","", "" )),
            -1, "", RelatedRepo(), -1, false, "", 0.0, -1,
            "", "", listOf(StudioRepo(-1, "", "", "")), "",
            "", "", "", listOf(""), "", "", ""
        )
}