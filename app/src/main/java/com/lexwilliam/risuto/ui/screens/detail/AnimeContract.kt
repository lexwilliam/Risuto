package com.lexwilliam.risuto.ui.screens.detail

import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.model.AnimeDetailPresentation

class AnimeContract {
    sealed class Event : ViewEvent {
        data class InsertAnimeHistory(val anime: AnimeDetailPresentation): Event()
        data class GetAnimeDetails(val accessToken: String, val id: Int): Event()
    }

    data class State(
        val malId: Int,
        val accessToken: String,
        val animeDetail: AnimeDetailPresentation,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}