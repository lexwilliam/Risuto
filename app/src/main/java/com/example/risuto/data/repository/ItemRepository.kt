package com.chun2maru.risutomvvm.data.repository

import android.util.Log
import com.chun2maru.risutomvvm.data.mapper.toDomain
import com.chun2maru.risutomvvm.data.remote.JikanService
import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.chun2maru.risutomvvm.domain.repository.IItemRepository
import com.example.risuto.domain.model.TopItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ItemRepository(
        private val jikanService: JikanService
): IItemRepository {

    override suspend fun getSearchResult(query: String): Flow<List<SearchAnime>> = flow  {
        val searchResponse = jikanService.getSearchResult(query)
        val items = mutableListOf<SearchAnime>()
        for (item in searchResponse.results){
            items.add(item.toDomain())
        }
        emit(items)
    }

    override suspend fun getTopResult(
        type: String,
        page: Int,
        subType: String
    ): Flow<List<TopItem>> = flow {
        val topResponse = jikanService.getTopResult(type, page, subType)
        val items = mutableListOf<TopItem>()
        for (item in topResponse.top) {
            items.add(item.toDomain())
        }
        emit(items)
    }

}