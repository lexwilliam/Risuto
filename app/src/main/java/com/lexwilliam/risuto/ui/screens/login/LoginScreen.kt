package com.lexwilliam.risuto.ui.screens.login

import android.content.Intent
import android.net.Uri
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.lexwilliam.risuto.MainActivity
import com.lexwilliam.risuto.util.Constants
import timber.log.Timber

@Composable
fun LoginScreen(
    authCode: String?,
    state: LoginContract.State,
    onEventSent: (LoginContract.Event) -> Unit
) {
    LoginContent(
        authCode = authCode,
        authTokenLink = state.authTokenLink,
        oAuthState = state.oAuthState,
        onEventSent = { onEventSent(it) },
        isLoading = state.isLoading
    )
}

@Composable
fun LoginContent(
    authCode: String?,
    authTokenLink: String,
    oAuthState: OAuthState,
    onEventSent: (LoginContract.Event) -> Unit,
    isLoading: Boolean
) {
    val context = LocalContext.current

    if(authCode != null && oAuthState == OAuthState.Idle && !isLoading) {
        onEventSent(LoginContract.Event.ReceivedAuthToken(authCode))
    }

    when(oAuthState) {
        is OAuthState.RedirectToAuth -> {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authTokenLink))
            context.startActivity(intent)
        }
        is OAuthState.OAuthSuccess -> {
            Timber.d("OAUTH SUCCESS")
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