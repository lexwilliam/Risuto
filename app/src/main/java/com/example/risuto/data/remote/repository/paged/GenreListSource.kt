package com.example.risuto.data.remote.repository.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chun2maru.risutomvvm.data.mapper.toDomain
import com.chun2maru.risutomvvm.data.repository.ListRepository
import com.chun2maru.risutomvvm.presentation.mapper.toPresentation
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.model.QuerySearch
import kotlinx.coroutines.flow.collect

class GenreListSource(
    private val listRepository: ListRepository
): PagingSource<Int, AnimeListPresentation>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeListPresentation> {
        return try {
            val nextPage = params.key ?: 1
            val searchResponse = listRepository.genreAnime(QuerySearch(genre = listRepository.currentGenre, order_by = "members"), nextPage)

            var data: List<AnimeListPresentation> = emptyList()
            searchResponse.collect {
                data = it.results.map { it.toDomain().toPresentation() }
            }

            LoadResult.Page(
                data = data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (data.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnimeListPresentation>): Int? {
        TODO("Not yet implemented")
    }
}