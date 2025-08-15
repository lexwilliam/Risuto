package com.lexwilliam.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.mapper.AnimeMapper
import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.model.remote.anime.SeasonList
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeRemoteSource: AnimeRemoteSource,
    private val animeMapper: AnimeMapper
): AnimeRepository {

    override suspend fun getTopAnime(): Flow<Anime> {
        return animeRemoteSource.getTopAnime().map { animeMapper.toDomain(it) }
    }

    override suspend fun getSeasonNow(): Flow<Anime> {
        return animeRemoteSource.getSeasonNow().map { animeMapper.toDomain(it) }
    }

    override suspend fun getSeason(year: Int, season: String): Flow<Anime> {
        return animeRemoteSource.getSeason(year, season).map { animeMapper.toDomain(it) }
    }

    override suspend fun getSeasonList(): Flow<SeasonList> {
        return animeRemoteSource.getSeasonList().map { animeMapper.toDomain(it) }
    }

    override suspend fun getSchedules(dayOfWeek: String): Flow<Anime> {
        return animeRemoteSource.getSchedules(dayOfWeek).map { animeMapper.toDomain(it) }
    }

    override suspend fun getSearchAnime(
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
    ): Flow<Anime> {
        return animeRemoteSource.getSearchAnime(page, limit, q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer).map { animeMapper.toDomain(it) }
    }

    override fun getSearchAnimePaging(
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
    ): Flow<PagingData<Anime.Data>> {
        return animeRemoteSource.getSearchAnimePaging(q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer).map { it.map { animeMapper.toDomain(it) } }
    }


}