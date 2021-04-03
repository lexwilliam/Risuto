package com.chun2maru.risutomvvm.domain.repository

import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.example.risuto.domain.model.TopAnime
import com.example.risuto.presentation.model.QuerySearch
import kotlinx.coroutines.flow.Flow

interface IListRepository {

    suspend fun searchAnime(query: QuerySearch): Flow<List<SearchAnime>>

    suspend fun topAnime(page: Int, subType: String): Flow<List<TopAnime>>
}