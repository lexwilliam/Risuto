package com.example.risuto.presentation.ui.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimePresentation

@Composable
fun AnimeScreen(
    viewModel: AnimeViewModel = viewModel()
) {
    val viewState by viewModel.state.collectAsState()
    AnimeContent(
        animeDetail = viewState.animeDetail
    )
}

@Composable
fun AnimeContent(
    animeDetail: AnimePresentation
) {
    Text(text = "This is Anime Screen (mal_id = ${animeDetail.mal_id})")
}

@Composable
fun AnimeDetail() {

}

@Composable
fun AnimeStaff() {

}

@Composable
fun AnimeRecommendation() {

}

