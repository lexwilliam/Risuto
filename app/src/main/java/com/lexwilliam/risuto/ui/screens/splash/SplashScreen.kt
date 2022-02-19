package com.lexwilliam.risuto.ui.screens.splash
import androidx.compose.runtime.*
import timber.log.Timber

@Composable
fun SplashScreen(
    state: SplashContract.State,
    onEventSent: (SplashContract.Event) -> Unit,
    navToLogin: () -> Unit,
    navToHome: () -> Unit
) {
    Timber.d("isLoading : ${state.isLoading}")
    Timber.d("isUserLoggedIn : ${state.isUserLoggedIn}")
    var isDone by remember { mutableStateOf(false) }
    if(!state.isLoading && !isDone) {
        if(state.isUserLoggedIn != null) {
            if(state.isUserLoggedIn) {
                navToHome()
            } else {
                navToLogin()
            }
            isDone = true
        } else {
            onEventSent(SplashContract.Event.SetupOAuth(state.refreshToken, state.expiresIn))
        }
    }
}