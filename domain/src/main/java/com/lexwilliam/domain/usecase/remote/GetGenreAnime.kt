package com.lexwilliam.domain.usecase.remote

import androidx.paging.PagingData
import com.lexwilliam.domain.model.remote.search.SearchAnime
import com.lexwilliam.domain.repository.AnimeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetGenreAnime {
    fun execute(q: String?, genre: Int?): Flow<PagingData<SearchAnime>>
}

class GetGenreAnimeImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetGenreAnime {
    override fun execute(q: String?, genre: Int?): Flow<PagingData<SearchAnime>> {
        return animeRepository.genreAnime(q, genre)
    }
}