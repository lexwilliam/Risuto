package com.chun2maru.risutomvvm.data.repository

import com.chun2maru.risutomvvm.data.mapper.toDomain
import com.chun2maru.risutomvvm.data.remote.JikanService
import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.chun2maru.risutomvvm.domain.repository.IListRepository
import com.example.risuto.domain.model.TopAnime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ListRepository(
        private val jikanService: JikanService
): IListRepository {

    override suspend fun searchAnime(query: String): Flow<List<SearchAnime>> = flow  {
        val searchResponse = jikanService.getSearchAnimeResult(query)
        val items = mutableListOf<SearchAnime>()
        for (item in searchResponse.results){
            items.add(item.toDomain())
        }
        emit(items)
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

}