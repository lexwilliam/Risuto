package com.lexwilliam.domain.usecase.remote.anime

import androidx.paging.PagingData
import com.lexwilliam.domain.model.remote.anime.Anime
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSearchAnimePaging {
    fun execute(
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

class GetSearchAnimePagingImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetSearchAnimePaging {
    override fun execute(
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
        return animeRepository.getSearchAnimePaging(q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer)
    }
}