package com.lexwilliam.risutov2.ui.detail

import com.lexwilliam.risutov2.base.ViewEvent
import com.lexwilliam.risutov2.base.ViewSideEffect
import com.lexwilliam.risutov2.base.ViewState
import com.lexwilliam.risutov2.model.detail.AnimeDetailPresentation
import com.lexwilliam.risutov2.model.detail.CharacterStaffPresentation
import com.lexwilliam.risutov2.model.local.MyAnimePresentation
import com.lexwilliam.risutov2.model.local.WatchStatusPresentation

class AnimeContract {
    sealed class Event : ViewEvent {
        data class InsertMyAnime(val anime: MyAnimePresentation): Event()
        data class InsertAnimeHistory(val anime: AnimeDetailPresentation): Event()
    }

    data class State(
        val animeDetail: AnimeDetailPresentation,
        val characterStaff: CharacterStaffPresentation,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}