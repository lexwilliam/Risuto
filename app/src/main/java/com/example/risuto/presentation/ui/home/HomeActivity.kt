package com.example.risuto.presentation.ui.home

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import com.example.risuto.ui.theme.RisutoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RisutoTheme {
                Surface {

                }
            }
        }
    }
}