package com.lexwilliam.risuto.ui.screens.profile

import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.model.UserAnimeListPresentation

class ProfileContract {
    sealed class Event : ViewEvent {
        data class GetUserInfo(val accessToken: String): Event()
        data class GetUserAnimeList(val accessToken: String): Event()
    }

    data class State(
        val animes: List<UserAnimeListPresentation.Data>,
        val accessToken: String,
        val username: String,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}