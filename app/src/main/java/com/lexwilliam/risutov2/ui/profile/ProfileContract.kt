package com.lexwilliam.risutov2.ui.profile

import com.lexwilliam.risutov2.base.ViewEvent
import com.lexwilliam.risutov2.base.ViewSideEffect
import com.lexwilliam.risutov2.base.ViewState
import com.lexwilliam.risutov2.model.AnimePresentation

class ProfileContract {
    sealed class Event : ViewEvent {}

    data class State(
        val myAnimes: List<AnimePresentation>,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}