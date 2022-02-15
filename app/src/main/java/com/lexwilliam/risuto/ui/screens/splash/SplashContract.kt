package com.lexwilliam.risuto.ui.screens.splash

import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.ui.screens.login.OAuthState

class SplashContract {
    sealed class Event : ViewEvent {}

    data class State(
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}