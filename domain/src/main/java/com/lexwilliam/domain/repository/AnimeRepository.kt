package com.chun2maru.risutomvvm.domain.repository

import com.lexwilliam.domain.model.SearchAnime
import com.example.risuto.data.remote.model.list.request.RequestSearch
import com.lexwilliam.domain.model.SeasonAnime
import com.lexwilliam.domain.model.TopAnime
import com.example.risuto.presentation.model.QuerySearch
import com.lexwilliam.domain.model.remote.search.Search
import com.lexwilliam.domain.model.remote.season.Season
import com.lexwilliam.domain.model.remote.top.Top
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    suspend fun searchAnime(q: String?, type: String?, status: String?, genre: Int?, limit: Int?, orderBy: String?, sort: String?, page: Int?): Flow<List<Search>>

    suspend fun genreAnime(q: String?, type: String?, status: String?, genre: Int?, limit: Int?, orderBy: String?, sort: String?, page: Int?): Flow<Search>

    suspend fun topAnime(page: Int, subType: String): Flow<List<Top>>

    suspend fun seasonAnime(year: Int, season: String): Flow<List<Season>>
}