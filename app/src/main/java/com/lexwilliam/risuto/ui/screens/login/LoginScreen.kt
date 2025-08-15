package com.lexwilliam.risuto.ui.screens.login

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.lexwilliam.risuto.R
import timber.log.Timber

@Composable
fun LoginScreen(
    authCode: String?,
    state: LoginContract.State,
    onEventSent: (LoginContract.Event) -> Unit,
    navToHome: () -> Unit,
    onDeleteAuthCode: () -> Unit
) {
    if(!state.isLoading) {
        LoginContent(
            authCode = authCode,
            authTokenLink = state.authTokenLink,
            oAuthState = state.oAuthState,
            onEventSent = { onEventSent(it) },
            navToHome = { navToHome() },
            onDeleteAuthCode = onDeleteAuthCode
        )
    }
}

@Composable
fun LoginContent(
    authCode: String?,
    authTokenLink: String,
    oAuthState: OAuthState,
    onEventSent: (LoginContract.Event) -> Unit,
    navToHome: () -> Unit,
    onDeleteAuthCode: () -> Unit
) {
    val context = LocalContext.current

    if(authCode != null && oAuthState == OAuthState.Idle) {
        onEventSent(LoginContract.Event.ReceivedAuthToken(authCode))
    }

    LaunchedEffect(oAuthState) {
        Timber.d(oAuthState.toString())
        when(oAuthState) {
            is OAuthState.RedirectToAuth -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authTokenLink))
                context.startActivity(intent)
            }
            is OAuthState.OAuthSuccess -> {
                Timber.d("OAUTH SUCCESS")
                navToHome()
                onDeleteAuthCode()
                onEventSent(LoginContract.Event.Done)
            }
            is OAuthState.OAuthFailure -> {
                Timber.d("OAUTH ERROR")
            }
            else -> Unit
        }
    }
    Column(
        modifier = Modifier
            .navigationBarsWithImePadding()
            .background(MaterialTheme.colors.primary)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.anime),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .padding(horizontal = 16.dp)
                .height(300.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text(text = "Let's Get Started", style = MaterialTheme.typography.h2, fontWeight = FontWeight.Black)
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onEventSent(LoginContract.Event.RedirectToAuth) }
            ) {
                Text(text = "Sign in with MyAnimeList", style = MaterialTheme.typography.button, fontWeight = FontWeight.SemiBold)
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onEventSent(LoginContract.Event.ContinueAsGuest) }
            ) {
                Text(text = "Continue as Guest", style = MaterialTheme.typography.button, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
