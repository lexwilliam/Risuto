package com.lexwilliam.data

import androidx.paging.PagingData
import com.lexwilliam.data.model.remote.anime.AnimeRepo
import kotlinx.coroutines.flow.Flow

interface AnimeRemoteSource {

    suspend fun getTopAnime(): Flow<AnimeRepo>

    suspend fun getSeasonNow(): Flow<AnimeRepo>

    suspend fun getSeason(year: Int, season: String): Flow<AnimeRepo>

    suspend fun getSchedules(dayOfWeek: String): Flow<AnimeRepo>

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
    ): Flow<AnimeRepo>

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
    ): Flow<PagingData<AnimeRepo.Data>>
}