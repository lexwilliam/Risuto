package com.example.risuto.presentation.ui.list

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Surface
import com.chun2maru.risutomvvm.presentation.viewmodel.ListViewModel
import com.example.risuto.ui.theme.RisutoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    private val listViewModel by viewModels<ListViewModel>()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RisutoTheme {
                Surface {
                    ListScreen(
                        animes = listViewModel.searchAnimeViewState,
                        items = listViewModel.getTopResultViewState,
                        executeAnimeSearch = listViewModel::executeAnimeSearch,
                        executeTopResult = listViewModel::executeTopResult
                    )
                }
            }
        }
    }
}