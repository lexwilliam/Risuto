package com.lexwilliam.risutov2.ui.genre

import androidx.paging.PagingData
import com.lexwilliam.domain.model.remote.search.SearchAnime
import com.lexwilliam.risutov2.base.ViewEvent
import com.lexwilliam.risutov2.base.ViewSideEffect
import com.lexwilliam.risutov2.base.ViewState
import com.lexwilliam.risutov2.model.AnimePresentation
import com.lexwilliam.risutov2.ui.search.SearchContract
import kotlinx.coroutines.flow.Flow

class GenreContract {
    sealed class Event : ViewEvent {}

    data class State(
        val animes: Flow<PagingData<SearchAnime>>? = null,
        val genreId: Int = 0,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}