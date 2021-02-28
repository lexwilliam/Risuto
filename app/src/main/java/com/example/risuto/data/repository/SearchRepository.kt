package com.chun2maru.risutomvvm.data.repository

import com.chun2maru.risutomvvm.data.mapper.toDomain
import com.chun2maru.risutomvvm.data.remote.AnimeService
import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.chun2maru.risutomvvm.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepository(
        private val animeService: AnimeService
): ISearchRepository {

    override suspend fun getSearchResult(query: String): Flow<List<SearchAnime>> = flow  {
        val searchResponse = animeService.getSearchResult(query)
        val items = mutableListOf<SearchAnime>()
        for (item in searchResponse.results){
            items.add(item.toDomain())
        }
        emit(items)
    }

}