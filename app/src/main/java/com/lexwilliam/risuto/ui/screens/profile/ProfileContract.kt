package com.lexwilliam.risuto.ui.screens.profile

import com.lexwilliam.risuto.ui.base.ViewEvent
import com.lexwilliam.risuto.ui.base.ViewSideEffect
import com.lexwilliam.risuto.ui.base.ViewState
import com.lexwilliam.risuto.model.UserProfilePresentation

class ProfileContract {
    sealed class Event : ViewEvent {
    }

    data class State(
        val userProfile: UserProfilePresentation.Data,
        val isLoading: Boolean,
        val isError: Boolean
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}