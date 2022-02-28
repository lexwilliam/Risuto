package com.lexwilliam.risuto.ui.screens.search

import androidx.paging.PagingData
import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.model.AnimeListPresentation
import com.lexwilliam.risuto.model.local.SearchHistoryPresentation
import com.lexwilliam.risuto.model.remote.AnimePresentation
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
        data class InsertSearchHistory(val query: String): Event()
        data class DeleteSearchHistory(val query: String): Event()
        object DeleteAllSearchHistory: Event()
        data class DeleteAnimeHistoryByTitle(val title: String): Event()
        object DeleteAllAnimeHistory: Event()

    }

    data class State(
        val searchAnimes: List<AnimePresentation.Data>,
        val searchAnimesPaging: Flow<PagingData<AnimePresentation.Data>>?,
        val animeHistory: List<AnimeListPresentation>,
        val searchHistory: List<SearchHistoryPresentation>,
        val genreFromArgs: String,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}