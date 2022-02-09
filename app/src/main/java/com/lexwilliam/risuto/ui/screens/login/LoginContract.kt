package com.lexwilliam.risuto.ui.screens.login

import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState

class LoginContract {
    sealed class Event : ViewEvent {
        object RedirectToAuth: Event()
        data class ReceivedAuthToken(val code: String): Event()
        object AuthCodeGotten: Event()
    }

    data class State(
        val oAuthState: OAuthState,
        val authTokenLink: String,
        val codeChallenge: String,
        val state: String,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}

sealed class OAuthState {
    object Idle : OAuthState()
    data class RedirectToAuth(val codeChallenge: String, val state: String) : OAuthState()
    object CodeGotten : OAuthState()
    object OAuthSuccess : OAuthState()
    data class OAuthFailure(val message: String) : OAuthState()
}