package com.chun2maru.risutomvvm.domain.repository

import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.example.risuto.domain.model.Anime
import com.example.risuto.domain.model.TopAnime
import kotlinx.coroutines.flow.Flow

interface IListRepository {

    suspend fun searchAnime(query: String): Flow<List<SearchAnime>>

    suspend fun topAnime(type: String, page: Int, subType: String): Flow<List<TopAnime>>
}