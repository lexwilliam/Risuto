package com.lexwilliam.domain.repository

import androidx.paging.PagingData
import com.lexwilliam.domain.model.remote.anime.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    suspend fun getAnimeById(id: Int): Flow<Anime.Data>

    suspend fun getTopAnime(): Flow<Anime>

    suspend fun getSeasonNow(): Flow<Anime>

    suspend fun getSeason(year: Int, season: String): Flow<Anime>

    suspend fun getSchedules(dayOfWeek: String): Flow<Anime>

    suspend fun getSearchAnime(
        page: Int?,
        limit: Int?,
        q: String?,
        type: String?,
        score: Double?,
        minScore: Double?,
        maxScore: Double?,
        status: String?,
        rating: String?,
        sfw: Boolean?,
        genres: String?,
        genresExclude: String?,
        orderBy: String?,
        sort: String?,
        letter: String?,
        producer: String?
    ): Flow<Anime>

    fun getSearchAnimePaging(
        q: String?,
        type: String?,
        score: Double?,
        minScore: Double?,
        maxScore: Double?,
        status: String?,
        rating: String?,
        sfw: Boolean?,
        genres: String?,
        genresExclude: String?,
        orderBy: String?,
        sort: String?,
        letter: String?,
        producer: String?
    ): Flow<PagingData<Anime.Data>>
}