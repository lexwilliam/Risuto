package com.chun2maru.risutomvvm.data.repository

import com.chun2maru.risutomvvm.data.mapper.toDomain
import com.chun2maru.risutomvvm.data.remote.JikanService
import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.chun2maru.risutomvvm.domain.repository.IListRepository
import com.example.risuto.data.remote.model.list.SearchAnimeResponse
import com.example.risuto.data.remote.model.list.request.RequestSearch
import com.example.risuto.domain.model.SeasonAnime
import com.example.risuto.domain.model.SeasonArchive
import com.example.risuto.domain.model.TopAnime
import com.example.risuto.presentation.model.QuerySearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ListRepository(
    private val jikanService: JikanService
): IListRepository {

    override suspend fun searchAnime(query: QuerySearch): Flow<List<SearchAnime>> = flow {
        val searchResponse = jikanService.getSearchAnimeResult(
            q = query.q,
            type = query.type,
            status = query.status,
            genre = query.genre,
            limit = query.limit,
            orderBy = query.order_by,
            sort = query.sort,
            page = 1
        )
        val items = mutableListOf<SearchAnime>()
        for (item in searchResponse.results){
            items.add(item.toDomain())
        }
        emit(items)
    }

    var currentGenre = -1

    override suspend fun genreAnime(query: QuerySearch, page: Int): Flow<RequestSearch> = flow  {
        val searchResponse = jikanService.getSearchAnimeResult(
            q = query.q,
            type = query.type,
            status = query.status,
            genre = query.genre,
            limit = query.limit,
            orderBy = query.order_by,
            sort = query.sort,
            page = page
        )
//        val items = mutableListOf<SearchAnime>()
//        for (item in searchResponse.results){
//            items.add(item.toDomain())
//        }
        emit(searchResponse)
    }

    override suspend fun topAnime(
        page: Int,
        subType: String
    ): Flow<List<TopAnime>> = flow {
        val topResponse = jikanService.getTopResult(page, subType)
        val items = mutableListOf<TopAnime>()
        for (item in topResponse.top) {
            items.add(item.toDomain())
        }
        emit(items)
    }

    override suspend fun seasonAnime(
        year: Int, season: String
    ): Flow<List<SeasonAnime>> = flow {
        val seasonResponse = jikanService.getSeasonAnimeResult(year, season)
        val items = mutableListOf<SeasonAnime>()
        for (item in seasonResponse.anime) {
            items.add(item.toDomain())
        }
        emit(items)
    }
}