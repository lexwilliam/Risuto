package com.lexwilliam.risuto.ui.screens.profile

import com.lexwilliam.risuto.ui.base.ViewEvent
import com.lexwilliam.risuto.ui.base.ViewSideEffect
import com.lexwilliam.risuto.ui.base.ViewState
import com.lexwilliam.risuto.model.UserAnimeListPresentation

class MyAnimeContract {
    sealed class Event : ViewEvent {
        object RefreshList: Event()
        object RefreshListWithoutView: Event()
        data class UpdateUserAnimeStatus(val id: Int, val numEpisodesWatched: Int, val status: String, val score: Int): Event()
    }

    data class State(
        val animes: List<UserAnimeListPresentation.Data>,
        val username: String,
        val userImage: String,
        val isRefreshing: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val isGuest: Boolean = false,
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}