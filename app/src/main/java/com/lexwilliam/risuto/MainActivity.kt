package com.lexwilliam.risuto

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.lexwilliam.risuto.ui.theme.RisutoTheme
import com.lexwilliam.risuto.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var authCode: String? = null

    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RisutoTheme {
                RisutoApp(
                    authCode = authCode
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val uri = intent?.data
        Timber.d("onResume uri:$uri")
        if (uri != null && uri.toString().startsWith(Constants.redirectUri)) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                authCode = code
            }
        }
    }
}