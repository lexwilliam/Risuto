package com.example.risuto.presentation.ui.detail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import com.example.risuto.ui.theme.RisutoTheme

class DetailActivity: AppCompatActivity() {

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