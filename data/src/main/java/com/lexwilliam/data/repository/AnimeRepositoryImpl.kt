package com.lexwilliam.data.repository

import androidx.paging.*
import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.mapper.AnimeMapper
import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.model.remote.search.Search
import com.lexwilliam.domain.model.remote.search.SearchAnime
import com.lexwilliam.domain.model.remote.season.Season
import com.lexwilliam.domain.model.remote.top.Top
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeRemoteSource: AnimeRemoteSource,
    private val animeMapper: AnimeMapper
): AnimeRepository {

    override suspend fun searchAnime(
        q: String?,
        type: String?,
        status: String?,
        genre: Int?,
        limit: Int?,
        orderBy: String?,
        sort: String?,
        page: Int?
    ): Flow<Search> {
        return animeRemoteSource.searchAnime(q, type, status, genre, limit, orderBy, sort, page).map { animeMapper.toDomain(it) }
    }

    override fun genreAnime(
        q: String?,
        type: String?,
        status: String?,
        genre: Int?,
        orderBy: String?,
        sort: String?
    ): Flow<PagingData<SearchAnime>> {
        return animeRemoteSource.genreAnime(q, type, status, genre, orderBy, sort).map { it.map { animeMapper.toDomain(it) } }
    }

    override suspend fun getTopAnime(): Flow<Anime> {
        return animeRemoteSource.getTopAnime().map { animeMapper.toDomain(it) }
    }

    override suspend fun getSeasonNow(): Flow<Anime> {
        return animeRemoteSource.getSeasonNow().map { animeMapper.toDomain(it) }
    }

    override suspend fun getSeason(year: Int, season: String): Flow<Anime> {
        return animeRemoteSource.getSeason(year, season).map { animeMapper.toDomain(it) }
    }

    override suspend fun getSchedules(dayOfWeek: String): Flow<Anime> {
        return animeRemoteSource.getSchedules(dayOfWeek).map { animeMapper.toDomain(it) }
    }

    override suspend fun getSearchAnime(
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
    ): Flow<Anime> {
        return animeRemoteSource.getSearchAnime(page, limit, q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer).map { animeMapper.toDomain(it) }
    }

    override suspend fun getAnimeById(id: Int): Flow<Anime.Data> {
        return animeRemoteSource.getAnimeById(id).map { animeMapper.toDomain(it) }
    }


}