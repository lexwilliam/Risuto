package com.lexwilliam.risuto

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.lexwilliam.risuto.ui.RisutoApp
import com.lexwilliam.risuto.ui.theme.RisutoTheme
import com.lexwilliam.risuto.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var authCode: String? = null
    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class,
        ExperimentalFoundationApi::class
    )
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