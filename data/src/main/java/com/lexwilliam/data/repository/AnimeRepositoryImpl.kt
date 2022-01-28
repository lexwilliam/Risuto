package com.lexwilliam.data.repository

import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.mapper.AnimeMapper
import com.lexwilliam.domain.model.remote.search.Search
import com.lexwilliam.domain.model.remote.season.Season
import com.lexwilliam.domain.model.remote.top.Top
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun genreAnime(
        q: String?,
        type: String?,
        status: String?,
        genre: Int?,
        limit: Int?,
        orderBy: String?,
        sort: String?,
        page: Int?
    ): Flow<Search> {
        return animeRemoteSource.genreAnime(q, type, status, genre, limit, orderBy, sort, page).map { animeMapper.toDomain(it) }
    }

    override suspend fun topAnime(page: Int, subType: String): Flow<Top> {
        return animeRemoteSource.topAnime(page, subType).map { animeMapper.toDomain(it) }
    }

    override suspend fun currentSeasonAnime(): Flow<Season> {
        return animeRemoteSource.currentSeasonAnime().map { animeMapper.toDomain(it) }
    }

    override suspend fun seasonAnime(year: Int?, season: String?): Flow<Season> {
        return animeRemoteSource.seasonAnime(year, season).map { animeMapper.toDomain(it) }
    }


}