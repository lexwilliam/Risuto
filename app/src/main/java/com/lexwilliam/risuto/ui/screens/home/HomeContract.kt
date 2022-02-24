package com.lexwilliam.risuto.ui.screens.home

import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.model.AnimeListPresentation
import com.lexwilliam.risuto.model.remote.AnimePresentation

class HomeContract {
    sealed class Event : ViewEvent {
        object LoadingDone: Event()
    }

    data class State(
        val username: String,
        val accessToken: String,
        val airingTodayAnime: List<AnimeListPresentation>,
        val currentSeason: String,
        val currentYear: Int,
        val seasonAnime: List<AnimePresentation.Data>,
        val topAnime: List<AnimePresentation.Data>,
        val schedules: List<AnimePresentation.Data>,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}