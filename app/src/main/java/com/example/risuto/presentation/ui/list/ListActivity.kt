package com.example.risuto.presentation.ui.list

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import com.chun2maru.risutomvvm.presentation.viewmodel.SearchViewModel
import com.example.risuto.ui.theme.RisutoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    val searchViewModel by viewModels<SearchViewModel>()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RisutoTheme {
                Surface {
                    ListScreen(
                        animes = searchViewModel.searchAnimeViewState,
                        executeAnimeSearch = searchViewModel::executeAnimeSearch)
                }
            }
        }
    }
}