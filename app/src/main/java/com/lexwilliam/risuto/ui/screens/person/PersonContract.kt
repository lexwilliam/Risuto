package com.lexwilliam.risuto.ui.screens.person

import com.lexwilliam.risuto.model.PersonPresentation
import com.lexwilliam.risuto.ui.base.ViewEvent
import com.lexwilliam.risuto.ui.base.ViewSideEffect
import com.lexwilliam.risuto.ui.base.ViewState

class PersonContract {
    sealed class Event: ViewEvent {

    }

    data class State(
        val person: PersonPresentation.Data,
        val isLoading: Boolean,
        val isError: Boolean
    ): ViewState

    sealed class Effect: ViewSideEffect {}
}