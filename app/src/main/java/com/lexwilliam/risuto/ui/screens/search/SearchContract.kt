package com.lexwilliam.risuto.ui.screens.search

import androidx.paging.PagingData
import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.model.local.SearchHistoryPresentation
import kotlinx.coroutines.flow.Flow

class SearchContract {
    sealed class Event : ViewEvent {
        data class SearchAnime(val q: String?): Event()
        data class SearchAnimePaging(val q: String?, val type: String?, val status: String?, val genre: Int?, val orderBy: String?, val sort: String?): Event()
        data class InsertSearchHistory(val query: String): Event()
        data class DeleteSearchHistory(val query: String): Event()
        object DeleteAllSearchHistory: Event()
        data class DeleteAnimeHistoryByTitle(val title: String): Event()
        object DeleteAllAnimeHistory: Event()

    }

    data class State(
        val searchAnimes: List<AnimePresentation>,
        val searchAnimesPaging: Flow<PagingData<AnimePresentation>>?,
        val animeHistory: List<AnimePresentation>,
        val searchHistory: List<SearchHistoryPresentation>,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}