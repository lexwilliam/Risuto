package com.lexwilliam.risuto.ui.screens.search

import androidx.paging.PagingData
import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.model.SearchHistoryPresentation
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.model.ShortAnimePresentation
import kotlinx.coroutines.flow.Flow

class SearchContract {
    sealed class Event : ViewEvent {
        data class SearchAnime(
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
            val producer: String?
        ): Event()
        data class SearchAnimePaging(
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
            val producer: String?
        ): Event()
        data class RefreshPaging(
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
            val producer: String?
        ): Event()
        data class InsertSearchHistory(val query: String): Event()
        data class DeleteSearchHistory(val query: String): Event()
        object DeleteAllSearchHistory: Event()
        data class DeleteAnimeHistoryByTitle(val title: String): Event()
        object DeleteAllAnimeHistory: Event()


    }

    data class State(
        val searchAnimes: List<AnimePresentation.Data>,
        val searchAnimesPaging: Flow<PagingData<AnimePresentation.Data>>?,
        val animeHistory: List<ShortAnimePresentation>,
        val searchHistory: List<SearchHistoryPresentation>,
        val genreFromArgs: String,
        val isRefreshing: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}