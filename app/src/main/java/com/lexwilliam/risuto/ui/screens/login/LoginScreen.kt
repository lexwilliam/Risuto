package com.lexwilliam.risuto.ui.screens.login

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
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

@OptIn(ExperimentalMaterialApi::class)
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
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetGesturesEnabled = false,
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetElevation = 16.dp,
        sheetContent = {
            LaunchedEffect(bottomSheetScaffoldState) {
                bottomSheetScaffoldState.bottomSheetState.expand()
            }
            Column(
                modifier = Modifier
                    .navigationBarsWithImePadding()
                    .padding(horizontal = 16.dp, vertical = 72.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "Let's Get Started", style = MaterialTheme.typography.h2, fontWeight = FontWeight.Black)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onEventSent(LoginContract.Event.RedirectToAuth) }
                ) {
                    Text(text = "Sign in with MyAnimeList", style = MaterialTheme.typography.button, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .navigationBarsWithImePadding()
                .background(MaterialTheme.colors.primary)
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.anime),
                contentDescription = null
            )
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