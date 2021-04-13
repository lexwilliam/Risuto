package com.example.risuto.presentation.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.ui.component.Header
import com.example.risuto.presentation.ui.component.HorizontalGridList
import com.example.risuto.presentation.util.Error
import com.example.risuto.presentation.util.generateFakeItemList
import com.example.risuto.presentation.util.getCurrentYear
import com.example.risuto.presentation.util.thisSeason
import java.util.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navToDetail: (Int) -> Unit,
    navToSearch: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()

    HomeContent(
        currentSeasonAnime = viewState.currentSeasonAnime,
        topAiringAnime = viewState.topAiringAnime,
        topAnime = viewState.topAnime,
        topUpcomingAnime = viewState.topUpcomingAnime,
        onError = viewState.error,
        navToDetail = navToDetail
    )
}

@Composable
fun HomeContent(
    currentSeasonAnime: List<AnimeListPresentation>,
    topAiringAnime: List<AnimeListPresentation>,
    topAnime: List<AnimeListPresentation>,
    topUpcomingAnime: List<AnimeListPresentation>,
    onError: Error?,
    navToDetail: (Int) -> Unit
) {
    val state = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(state)
            .padding(start = 16.dp, bottom = 64.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HomeTopBar()
        PosterGridList(
            title = thisSeason.capitalize(Locale.ROOT) + " " + getCurrentYear() + " " + "Anime",
            items = currentSeasonAnime,
            navToDetail = { navToDetail(it) }
        )
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
    navToDetail: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        HorizontalGridList(
            items = items,
            navToDetail = { navToDetail(it) }
        )
    }
}

@Composable
fun HomeTopBar() {
    Header(title = "Home")
}

@Preview
@Composable
fun HomeContentPreview() {
    HomeContent(
        currentSeasonAnime = generateFakeItemList(),
        topAiringAnime = generateFakeItemList(),
        topAnime = generateFakeItemList(),
        topUpcomingAnime = generateFakeItemList(),
        onError = null,
        navToDetail = {}
    )
}


