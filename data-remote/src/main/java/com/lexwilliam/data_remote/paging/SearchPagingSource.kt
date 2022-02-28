package com.lexwilliam.data_remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lexwilliam.data_remote.JikanV4Service
import com.lexwilliam.data_remote.data.AnimeRemoteSourceImpl
import com.lexwilliam.data_remote.model.anime.AnimeResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val jikanService: JikanV4Service,
    private val q: String?,
    private val type: String?,
    private val score: Double?,
    private val minScore: Double?,
    private val maxScore: Double?,
    private val status: String?,
    private val rating: String?,
    private val sfw: Boolean?,
    private val genres: String?,
    private val genresExclude: String?,
    private val orderBy: String?,
    private val sort: String?,
    private val letter: String?,
    private val producer: String?
): PagingSource<Int, AnimeResponse.Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeResponse.Data> {
        val page = params.key ?: AnimeRemoteSourceImpl.DEFAULT_PAGE_INDEX
        return try {
            val response = jikanService.getSearchAnime(page, params.loadSize, q, type, score, minScore, maxScore, status, rating, sfw, genres, genresExclude, orderBy, sort, letter, producer)
            LoadResult.Page(
                response.data, prevKey = if (page == AnimeRemoteSourceImpl.DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.pagination.has_next_page) page + 1 else null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnimeResponse.Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}