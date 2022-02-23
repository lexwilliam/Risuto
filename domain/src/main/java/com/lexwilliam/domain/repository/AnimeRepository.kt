package com.lexwilliam.domain.repository

import androidx.paging.PagingData
import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.model.remote.search.Search
import com.lexwilliam.domain.model.remote.search.SearchAnime
import com.lexwilliam.domain.model.remote.season.Season
import com.lexwilliam.domain.model.remote.top.Top
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    suspend fun searchAnime(q: String?, type: String?, status: String?, genre: Int?, limit: Int?, orderBy: String?, sort: String?, page: Int?): Flow<Search>

    fun genreAnime(q: String?, type: String?, status: String?, genre: Int?, orderBy: String?, sort: String?,): Flow<PagingData<SearchAnime>>

    suspend fun topAnime(page: Int, subType: String): Flow<Top>

    suspend fun getTopAnime(): Flow<Anime>

    suspend fun currentSeasonAnime(): Flow<Season>

    suspend fun seasonAnime(year: Int?, season: String?): Flow<Season>
}