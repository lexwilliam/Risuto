package com.lexwilliam.risuto.ui.screens.splash

import androidx.compose.runtime.Composable
import timber.log.Timber

@Composable
fun SplashScreen(
    state: SplashContract.State,
    onEventSent: (SplashContract.Event) -> Unit,
    navToLogin: () -> Unit,
    navToHome: () -> Unit
) {
    if(state.accessToken != "" && state.refreshToken != "" && state.expiresIn != -1L) {
        Timber.d("accessToken : ${state.accessToken}")
        Timber.d("refreshToken : ${state.refreshToken}")
        Timber.d("expiresIn : ${state.expiresIn}")
        onEventSent(SplashContract.Event.SetupOAuth(state.accessToken, state.refreshToken, state.expiresIn))
    }
    if(state.isTokenValid != null) {
        if(state.isTokenValid) {
            navToHome()
        } else {
            navToLogin()
        }
    }
}