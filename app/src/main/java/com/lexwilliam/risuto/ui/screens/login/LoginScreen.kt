package com.lexwilliam.risuto.ui.screens.login

import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import timber.log.Timber

@Composable
fun LoginScreen(
    authCode: String?,
    state: LoginContract.State,
    onEventSent: (LoginContract.Event) -> Unit,
    navToHome: () -> Unit
) {
    if(!state.isLoading) {
        LoginContent(
            authCode = authCode,
            authTokenLink = state.authTokenLink,
            oAuthState = state.oAuthState,
            onEventSent = { onEventSent(it) },
            isLoading = state.isLoading,
            navToHome = { navToHome() }
        )
    }
}

@Composable
fun LoginContent(
    authCode: String?,
    authTokenLink: String,
    oAuthState: OAuthState,
    onEventSent: (LoginContract.Event) -> Unit,
    isLoading: Boolean,
    navToHome: () -> Unit
) {
    val context = LocalContext.current

    if(authCode != null && oAuthState == OAuthState.Idle) {
        onEventSent(LoginContract.Event.ReceivedAuthToken(authCode))
    }

    when(oAuthState) {
        is OAuthState.RedirectToAuth -> {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authTokenLink))
            context.startActivity(intent)
        }
        is OAuthState.OAuthSuccess -> {
            Timber.d("OAUTH SUCCESS")
            navToHome()
            onEventSent(LoginContract.Event.Done)
        }
        is OAuthState.OAuthFailure -> {
            Timber.d("OAUTH ERROR")
        }
        else -> Unit
    }

    Button(onClick = { onEventSent(LoginContract.Event.RedirectToAuth) }) {
        Text(text = "Sign in")
    }
}