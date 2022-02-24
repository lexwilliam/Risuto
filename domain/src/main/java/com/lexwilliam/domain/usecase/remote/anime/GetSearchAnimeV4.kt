package com.lexwilliam.domain.usecase.remote.anime

import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSearchAnimeV4 {
    suspend fun execute(
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

class GetSearchAnimeV4Impl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetSearchAnimeV4 {
    override suspend fun execute(
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
        return animeRepository.getSearchAnime(page, limit, q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer)
    }
}