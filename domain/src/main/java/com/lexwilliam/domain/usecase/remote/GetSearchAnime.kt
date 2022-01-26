package com.lexwilliam.domain.usecase.remote

import com.lexwilliam.domain.model.remote.search.Search
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSearchAnime {
    suspend fun execute(q: String?, type: String?, status: String?, genre: Int?, limit: Int?, orderBy: String?, sort: String?, page: Int?): Flow<Search>
}

class GetSearchAnimeImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetSearchAnime {
    override suspend fun execute(q: String?, type: String?, status: String?, genre: Int?, limit: Int?, orderBy: String?, sort: String?, page: Int?): Flow<Search> {
        return animeRepository.searchAnime(q, type, status, genre, limit, orderBy, sort, page)
    }
}