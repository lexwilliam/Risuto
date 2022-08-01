package com.lexwilliam.risuto.ui.screens.search

import androidx.paging.PagingData
import com.lexwilliam.risuto.ui.base.ViewEvent
import com.lexwilliam.risuto.ui.base.ViewSideEffect
import com.lexwilliam.risuto.ui.base.ViewState
import com.lexwilliam.risuto.model.SearchHistoryPresentation
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.model.ShortAnimePresentation
import kotlinx.coroutines.flow.Flow

class SearchContract {
    sealed class Event : ViewEvent {
        data class OnQueryChanged(
            val q: String? = null,
            val type: String? = null,
            val score: Double? = null,
            val minScore: Double? = null,
            val maxScore: Double? = null,
            val status: String? = null,
            val rating: String? = null,
            val sfw: Boolean? = null,
            val genres: String? = null,
            val genresExclude: String? = null,
            val orderBy: String? = null,
            val sort: String? = null,
            val letter: String? = null,
            val producer: String? = null
        ): Event()
        object SearchAnime: Event()
        object SearchAnimePaging: Event()
        object RefreshPaging: Event()
        data class OnResultChanged(val resultType: ResultType): Event()
        data class InsertSearchHistory(val query: String): Event()
        data class DeleteSearchHistory(val query: String): Event()
        object DeleteAllSearchHistory: Event()
        data class DeleteAnimeHistoryByTitle(val title: String): Event()
        object DeleteAllAnimeHistory: Event()


    }

    data class State(
        val q: String?,
        val type: String?,
        val score: Double?,
        val minScore: Double?,
        val maxScore: Double?,
        val status: String?,
        val rating: String?,
        val sfw: Boolean?,
        val genres: String?,
        val genresExclude: String?,
        val orderBy: String?,
        val sort: String?,
        val letter: String?,
        val producer: String?,
        val resultType: ResultType,
        val searchAnimes: List<AnimePresentation.Data>,
        val searchAnimesPaging: Flow<PagingData<AnimePresentation.Data>>?,
        val animeHistory: List<ShortAnimePresentation>,
        val searchHistory: List<SearchHistoryPresentation>,
        val isRefreshing: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}