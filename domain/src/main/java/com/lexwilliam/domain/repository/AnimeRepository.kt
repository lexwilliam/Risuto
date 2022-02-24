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

    suspend fun getAnimeById(id: Int): Flow<Anime.Data>

    suspend fun getTopAnime(): Flow<Anime>

    suspend fun getSeasonNow(): Flow<Anime>

    suspend fun getSeason(year: Int, season: String): Flow<Anime>

    suspend fun getSchedules(dayOfWeek: String): Flow<Anime>

    suspend fun getSearchAnime(
        page: Int,
        limit: Int,
        q: String,
        type: String,
        score: Double,
        minScore: Double,
        maxScore: Double,
        status: String,
        rating: String,
        sfw: Boolean,
        genres: String,
        genresExclude: String,
        orderBy: String,
        sort: String,
        letter: String,
        producer: String
    ): Flow<Anime>

}