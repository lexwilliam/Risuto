package com.lexwilliam.risutov2.ui.search

import androidx.paging.PagingData
import com.lexwilliam.risutov2.base.ViewEvent
import com.lexwilliam.risutov2.base.ViewSideEffect
import com.lexwilliam.risutov2.base.ViewState
import com.lexwilliam.risutov2.model.AnimePresentation
import com.lexwilliam.risutov2.model.detail.AnimeDetailPresentation
import com.lexwilliam.risutov2.model.detail.CharacterStaffPresentation
import com.lexwilliam.risutov2.model.local.MyAnimePresentation
import com.lexwilliam.risutov2.model.local.SearchHistoryPresentation
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