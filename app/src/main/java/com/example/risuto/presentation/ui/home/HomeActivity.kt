package com.example.risuto.presentation.ui.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import com.chun2maru.risutomvvm.presentation.viewmodel.SearchViewModel
import com.example.risuto.ui.theme.RisutoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RisutoTheme {
                Surface {
                    HomeScreen(
                        animes = searchViewModel.searchAnimeViewState,
                        executeAnimeSearch = searchViewModel::executeAnimeSearch)
                }
            }
        }
    }
}