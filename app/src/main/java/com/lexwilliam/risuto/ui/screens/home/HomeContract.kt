package com.lexwilliam.risuto.ui.screens.home

import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.model.AnimePresentation

class HomeContract {
    sealed class Event : ViewEvent {}

    data class State(
        val username: String,
        val isTokenValid: Boolean?,
        val airingTodayAnime: List<AnimePresentation>,
        val currentSeason: String,
        val currentYear: Int,
        val seasonAnime: List<AnimePresentation>,
        val topAiringAnime: List<AnimePresentation>,
        val topUpcomingAnime: List<AnimePresentation>,
        val topAnime: List<AnimePresentation>,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}