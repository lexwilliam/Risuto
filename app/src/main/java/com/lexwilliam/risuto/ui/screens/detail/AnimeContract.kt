package com.lexwilliam.risuto.ui.screens.detail

import com.lexwilliam.risuto.model.AnimeCharactersPresentation
import com.lexwilliam.risuto.model.AnimeDetailPresentation
import com.lexwilliam.risuto.model.AnimeStaffPresentation
import com.lexwilliam.risuto.model.AnimeVideosPresentation
import com.lexwilliam.risuto.ui.base.ViewEvent
import com.lexwilliam.risuto.ui.base.ViewSideEffect
import com.lexwilliam.risuto.ui.base.ViewState

class AnimeContract {
    sealed class Event : ViewEvent {
        data class InsertAnimeHistory(val anime: AnimeDetailPresentation): Event()
        data class UpdateUserAnimeStatus(val id: Int, val numEpisodesWatched: Int, val status: String, val score: Int): Event()
        data class DeleteUserAnimeStatus(val id: Int): Event()
    }

    data class State(
        val malId: Int,
        val animeDetail: AnimeDetailPresentation,
        val myListStatus: AnimeDetailPresentation.MyListStatus,
        val characters: List<AnimeCharactersPresentation.Data>,
        val videos: AnimeVideosPresentation,
        val staff: List<AnimeStaffPresentation.Data>,
        val isGuest: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}