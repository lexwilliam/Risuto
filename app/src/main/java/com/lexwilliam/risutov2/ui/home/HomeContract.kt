package com.lexwilliam.risutov2.ui.home

import com.lexwilliam.risutov2.base.ViewEvent
import com.lexwilliam.risutov2.base.ViewSideEffect
import com.lexwilliam.risutov2.base.ViewState
import com.lexwilliam.risutov2.model.AnimePresentation

class HomeContract {
    sealed class Event : ViewEvent {}

    data class State(
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