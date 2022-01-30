package com.lexwilliam.data.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lexwilliam.domain.model.remote.search.SearchAnime
import com.lexwilliam.domain.repository.AnimeRepository
import com.lexwilliam.domain.doWhen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class GenreListSource @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val genre: Int
): PagingSource<Int, SearchAnime>() {

    companion object {
        const val jikanPerPage = 20
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchAnime> {
        return try {
            val nextPage = params.key ?: 1
            val perPage = params.loadSize
            var pagingResult: LoadResult<Int, SearchAnime>? = null

            CoroutineScope(Dispatchers.IO).launch {
                animeRepository.genreAnime(
                    q = null,
                    type = null,
                    status = null,
                    genre = genre,
                    limit = null,
                    orderBy = "members",
                    sort = "desc",
                    page = nextPage
                ).collect { animeResult ->
                    animeResult.doWhen(
                        onSuccess = { animes ->
                            val nextKey = if(jikanPerPage < perPage) null else nextPage + 1
                            pagingResult = LoadResult.Page(
                                data = animes.results,
                                prevKey = if(nextPage == 1) null else nextPage - 1,
                                nextKey = nextKey
                            )
                        },
                        onFail = { exception ->
                            pagingResult = LoadResult.Error(exception)
                        }
                    )
                }
            }.join()
            return pagingResult!!
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchAnime>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}