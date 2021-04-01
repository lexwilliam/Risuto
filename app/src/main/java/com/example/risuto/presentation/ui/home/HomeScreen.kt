package com.example.risuto.presentation.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.ui.component.ColumnList
import com.example.risuto.presentation.ui.component.HorizontalGridList

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navToDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()

    HomeContent(
        topAiringAnime = viewState.topAiringAnime,
        topAnime = viewState.topAnime,
        topUpcomingAnime = viewState.topUpcomingAnime,
        navToDetail = navToDetail
    )
}

@Composable
fun HomeContent(
    topAiringAnime: List<AnimeListPresentation>,
    topAnime: List<AnimeListPresentation>,
    topUpcomingAnime: List<AnimeListPresentation>,
    navToDetail: (Int) -> Unit
) {
    val state = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(state),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PosterGridList(
            title = "Top Airing",
            items = topAiringAnime,
            navToDetail = { navToDetail(it) }
        )
        PosterGridList(
            title = "Top Upcoming",
            items = topUpcomingAnime,
            navToDetail = { navToDetail(it) }
        )
        PosterGridList(
            title = "Top Anime",
            items = topAnime,
            navToDetail = { navToDetail(it) }
        )
    }
}

@Composable
fun PosterGridList(
    title: String,
    items: List<AnimeListPresentation>,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        HorizontalGridList(
            items = items,
            navToDetail = { navToDetail(it) }
        )
    }
}


