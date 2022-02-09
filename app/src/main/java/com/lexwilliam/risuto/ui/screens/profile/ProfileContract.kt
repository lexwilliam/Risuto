package com.lexwilliam.risuto.ui.screens.profile

import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.model.AnimePresentation

class ProfileContract {
    sealed class Event : ViewEvent {}

    data class State(
        val myAnimes: List<AnimePresentation>,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}