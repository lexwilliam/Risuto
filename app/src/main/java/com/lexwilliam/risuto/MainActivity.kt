package com.lexwilliam.risuto

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.insets.ProvideWindowInsets
import com.lexwilliam.risuto.ui.theme.RisutoTheme
import com.lexwilliam.risuto.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var authCode: String? = null
    private val viewModel: RisutoAppViewModel by viewModels()

    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.viewState.value.isLoading
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            RisutoTheme {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    RisutoApp(
                        authCode = authCode,
                        isUserLoggedIn = viewModel.viewState.value.isUserLoggedIn
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val uri = intent?.data
        if (uri != null && uri.toString().startsWith(Constants.redirectUri)) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                authCode = code
            }
        }
    }
}