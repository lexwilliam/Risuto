package com.lexwilliam.risuto.ui.screens.people

import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.model.PersonPresentation

class PeopleContract {
    sealed class Event: ViewEvent {

    }

    data class State(
        val person: PersonPresentation,
        val isLoading: Boolean,
        val isError: Boolean
    ): ViewState

    sealed class Effect: ViewSideEffect {}
}