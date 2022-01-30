package com.lexwilliam.risutov2.ui.search

import com.lexwilliam.risutov2.base.ViewEvent
import com.lexwilliam.risutov2.base.ViewSideEffect
import com.lexwilliam.risutov2.base.ViewState
import com.lexwilliam.risutov2.model.AnimePresentation
import com.lexwilliam.risutov2.model.detail.AnimeDetailPresentation
import com.lexwilliam.risutov2.model.detail.CharacterStaffPresentation
import com.lexwilliam.risutov2.model.local.MyAnimePresentation
import com.lexwilliam.risutov2.model.local.SearchHistoryPresentation

class SearchContract {
    sealed class Event : ViewEvent {
        data class SearchAnime(val q: String?, val type: String?, val status: String?, val genre: Int?, val limit: Int?, val orderBy: String?, val sort: String?, val page: Int?): Event()
        data class InsertSearchHistory(val query: String): Event()
        data class DeleteSearchHistory(val query: String): Event()
        object DeleteAllSearchHistory: Event()
        data class DeleteAnimeHistoryByTitle(val title: String): Event()
        object DeleteAllAnimeHistory: Event()

    }

    data class State(
        val searchAnimes: List<AnimePresentation>,
        val animeHistory: List<AnimePresentation>,
        val searchHistory: List<SearchHistoryPresentation>,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}