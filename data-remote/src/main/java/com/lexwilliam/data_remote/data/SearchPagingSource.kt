package com.lexwilliam.data_remote.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.data.AnimeRemoteSourceImpl.Companion.DEFAULT_PAGE_INDEX
import com.lexwilliam.data_remote.model.search.SearchAnimeResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val jikanService: JikanService,
    private val q: String?,
    private val type: String?,
    private val status: String?,
    private val orderBy: String?,
    private val sort: String?,
    private val genre: Int?
): PagingSource<Int, SearchAnimeResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchAnimeResponse> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = jikanService.getSearchAnimeResult(q, type, status, genre, params.loadSize, orderBy, sort, page)
            LoadResult.Page(
                response.results, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchAnimeResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}