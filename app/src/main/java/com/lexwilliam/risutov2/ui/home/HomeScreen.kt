package com.lexwilliam.risutov2.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.risutov2.model.AnimeListPresentation
import com.lexwilliam.risutov2.ui.component.Header
import com.lexwilliam.risutov2.ui.component.HorizontalGridList
import com.lexwilliam.risutov2.ui.component.LoadingScreen
import com.lexwilliam.risutov2.util.generateFakeItemList
import com.lexwilliam.risutov2.util.getCurrentSeason
import com.lexwilliam.risutov2.util.getCurrentYear
import java.util.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navToDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()

    HomeContent(
        currentSeasonAnime = viewState.currentSeasonAnime,
        topAiringAnime = viewState.topAiringAnime,
        topAnime = viewState.topAnime,
        topUpcomingAnime = viewState.topUpcomingAnime,
        navToDetail = navToDetail
    )
}

@Composable
fun HomeContent(
    currentSeasonAnime: List<AnimeListPresentation>,
    topAiringAnime: List<AnimeListPresentation>,
    topAnime: List<AnimeListPresentation>,
    topUpcomingAnime: List<AnimeListPresentation>,
    navToDetail: (Int) -> Unit
) {
    if(currentSeasonAnime.isEmpty() && topAiringAnime.isEmpty() && topAnime.isEmpty() && topUpcomingAnime.isEmpty()) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, bottom = 64.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Header(title = "Home", modifier = Modifier.padding(top = 24.dp))
            PosterGridList(
                title = _root_ide_package_.com.lexwilliam.risutov2.util.getCurrentSeason()
                    .capitalize(Locale.ROOT) + " " + _root_ide_package_.com.lexwilliam.risutov2.util.getCurrentYear() + " " + "Anime",
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
}

@Composable
fun PosterGridList(
    title: String,
    items: List<AnimeListPresentation>,
    navToDetail: (Int) -> Unit,
) {
    if(items.isEmpty()) {
        Box(modifier = Modifier
            .size(240.dp)
            .background(Color.Transparent))
    } else {
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
}

@Preview
@Composable
fun HomeContentPreview() {
    HomeContent(
        currentSeasonAnime = generateFakeItemList(),
        topAiringAnime = generateFakeItemList(),
        topAnime = generateFakeItemList(),
        topUpcomingAnime = generateFakeItemList(),
        navToDetail = {}
    )
}


