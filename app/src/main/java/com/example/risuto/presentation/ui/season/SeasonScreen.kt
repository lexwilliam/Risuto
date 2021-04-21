package com.example.risuto.presentation.ui.season

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.data.remote.model.detail.Archive
import com.example.risuto.presentation.model.AnimeListPresentation

@Composable
fun SeasonScreen(
    viewModel: SeasonViewModel = viewModel()
) {
    val viewState by viewModel.state.collectAsState()

    SeasonContent(
        archive = viewState.seasonArchive,
        animes = viewState.seasonAnimes
    )
}

@Composable
fun SeasonContent(
    archive: List<Archive>,
    animes: List<AnimeListPresentation>
) {

}

@Composable
fun SeasonToolBar(
    archive: List<Archive>
) {

}