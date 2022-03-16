package com.lexwilliam.risuto.ui.screens.login

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.lexwilliam.risuto.R
import com.lexwilliam.risuto.ui.component.StatusBarSpacer
import com.lexwilliam.risuto.ui.theme.RisutoTheme
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

    Column(
        modifier = Modifier
            .navigationBarsWithImePadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.anime),
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Let's Get Started", style = MaterialTheme.typography.h2, fontWeight = FontWeight.Black)
            Text(text = "Start exploring and tracking animes from MyAnimeList Database", style = MaterialTheme.typography.body1, color = Color.Gray, fontWeight = FontWeight.SemiBold)
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onEventSent(LoginContract.Event.RedirectToAuth) }
            ) {
                Text(text = "Sign in with MyAnimeList", style = MaterialTheme.typography.button, fontWeight = FontWeight.SemiBold)
            }
        }
    }


}

@Preview
@Composable
fun LoginScreenPreview() {
    RisutoTheme {
        LoginContent(
            authCode = "",
            authTokenLink = "",
            oAuthState = OAuthState.Idle,
            onEventSent = {},
            navToHome = {}
        )
    }
}