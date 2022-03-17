package com.lexwilliam.risuto

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.insets.ProvideWindowInsets
import com.lexwilliam.risuto.ui.theme.RisutoTheme
import com.lexwilliam.risuto.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var authCode: String? = null

    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            RisutoTheme {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    RisutoApp(
                        authCode = authCode
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