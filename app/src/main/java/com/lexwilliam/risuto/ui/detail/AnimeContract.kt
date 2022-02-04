package com.lexwilliam.risuto.ui.detail

import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.model.detail.AnimeDetailPresentation
import com.lexwilliam.risuto.model.detail.CharacterStaffPresentation
import com.lexwilliam.risuto.model.local.MyAnimePresentation

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