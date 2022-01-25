package com.chun2maru.risutomvvm.domain.repository

import com.lexwilliam.domain.model.SearchAnime
import com.example.risuto.data.remote.model.list.request.RequestSearch
import com.lexwilliam.domain.model.SeasonAnime
import com.lexwilliam.domain.model.TopAnime
import com.example.risuto.presentation.model.QuerySearch
import kotlinx.coroutines.flow.Flow

interface IListRepository {

    suspend fun searchAnime(query: QuerySearch): Flow<List<SearchAnime>>

    suspend fun genreAnime(query: QuerySearch, page: Int): Flow<RequestSearch>

    suspend fun topAnime(page: Int, subType: String): Flow<List<TopAnime>>

    suspend fun seasonAnime(year: Int, season: String): Flow<List<SeasonAnime>>

    suspend fun currentSeasonAnime(): Flow<List<SeasonAnime>>
}