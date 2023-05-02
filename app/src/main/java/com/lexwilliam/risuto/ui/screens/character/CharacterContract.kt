package com.lexwilliam.risuto.ui.screens.character

import com.lexwilliam.risuto.model.AnimeCharactersPresentation
import com.lexwilliam.risuto.model.AnimeDetailPresentation
import com.lexwilliam.risuto.model.AnimeStaffPresentation
import com.lexwilliam.risuto.model.AnimeVideosPresentation
import com.lexwilliam.risuto.model.CharacterDetailPresentation
import com.lexwilliam.risuto.ui.base.ViewEvent
import com.lexwilliam.risuto.ui.base.ViewSideEffect
import com.lexwilliam.risuto.ui.base.ViewState

class CharacterContract {
    sealed class Event : ViewEvent {

    }

    data class State(
        val character: CharacterDetailPresentation.Data,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}