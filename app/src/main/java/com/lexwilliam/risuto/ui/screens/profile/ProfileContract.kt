package com.lexwilliam.risuto.ui.screens.profile

import com.lexwilliam.risuto.model.UserProfilePresentation
import com.lexwilliam.risuto.ui.base.ViewEvent
import com.lexwilliam.risuto.ui.base.ViewSideEffect
import com.lexwilliam.risuto.ui.base.ViewState

class ProfileContract {
    sealed class Event : ViewEvent {
        object Logout: Event()
        object NavigationDone: Event()
    }

    data class State(
        val userProfile: UserProfilePresentation.Data,
        val isLoading: Boolean,
        val isError: Boolean,
        val isGuest: Boolean,
        val isLoggedOut: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}