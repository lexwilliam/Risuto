package com.chun2maru.risutomvvm.domain.repository

import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.example.risuto.domain.model.TopItem
import kotlinx.coroutines.flow.Flow

interface IItemRepository {

    suspend fun getSearchResult(query: String): Flow<List<SearchAnime>>

    suspend fun getTopResult(type: String, page: Int, subType: String): Flow<List<TopItem>>
}