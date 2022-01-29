package com.lexwilliam.risutov2.ui.season

import com.lexwilliam.risutov2.base.ViewEvent
import com.lexwilliam.risutov2.base.ViewSideEffect
import com.lexwilliam.risutov2.base.ViewState
import com.lexwilliam.risutov2.model.AnimePresentation
import com.lexwilliam.risutov2.model.detail.AnimeDetailPresentation
import com.lexwilliam.risutov2.model.detail.CharacterStaffPresentation
import com.lexwilliam.risutov2.model.local.MyAnimePresentation

class SeasonContract {
    sealed class Event : ViewEvent {}

    data class State(
        val season: String,
        val year: Int,
        val seasonAnimes: List<AnimePresentation>,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}