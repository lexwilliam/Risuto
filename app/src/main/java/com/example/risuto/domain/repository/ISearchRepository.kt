package com.chun2maru.risutomvvm.domain.repository

import com.chun2maru.risutomvvm.data.remote.model.RequestSearch
import com.chun2maru.risutomvvm.domain.model.SearchAnime
import java.util.concurrent.Flow

interface ISearchRepository {

    suspend fun getSearchResult(query: String): kotlinx.coroutines.flow.Flow<List<SearchAnime>>
}