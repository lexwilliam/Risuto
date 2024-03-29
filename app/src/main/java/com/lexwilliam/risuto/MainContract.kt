package com.lexwilliam.risuto

import com.lexwilliam.risuto.ui.base.ViewEvent
import com.lexwilliam.risuto.ui.base.ViewSideEffect
import com.lexwilliam.risuto.ui.base.ViewState

class MainContract {
    sealed class Event : ViewEvent {
        data class SetupOAuth(val refreshToken: String?, val expiresIn: Long): Event()
    }

    data class State(
        val isUserLoggedIn: Boolean? = null,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}