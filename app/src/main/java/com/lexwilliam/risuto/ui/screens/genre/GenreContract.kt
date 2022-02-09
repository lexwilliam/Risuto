package com.lexwilliam.risuto.ui.screens.genre

import androidx.paging.PagingData
import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.model.AnimePresentation
import kotlinx.coroutines.flow.Flow

class GenreContract {
    sealed class Event : ViewEvent {
        data class GetGenreAnimes(val genreId: Int): Event()
    }

    data class State(
        val animes: Flow<PagingData<AnimePresentation>>?,
        val genreId: Int = 0,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}