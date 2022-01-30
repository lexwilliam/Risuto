package com.lexwilliam.domain.usecase.remote

import androidx.paging.PagingData
import com.lexwilliam.domain.model.remote.search.Search
import com.lexwilliam.domain.model.remote.search.SearchAnime
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetGenreAnime {
    suspend fun execute(scope: CoroutineScope, genre: Int, perPage: Int): Flow<PagingData<SearchAnime>>
}

class GetGenreAnimeImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetGenreAnime {
    override suspend fun execute(scope: CoroutineScope, genre: Int, perPage: Int): Flow<PagingData<SearchAnime>> {
        return animeRepository.genrePagination(scope, genre, perPage)
    }
}