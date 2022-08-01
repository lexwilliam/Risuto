package com.lexwilliam.risuto.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.ui.component.*
import com.lexwilliam.risuto.ui.theme.RisutoTheme
import com.lexwilliam.risuto.util.FakeItems
import timber.log.Timber
import java.util.*

@Composable
fun HomeScreen(
    state: HomeContract.State,
    onEventSent: (HomeContract.Event) -> Unit,
    navToDetail: (Int) -> Unit
) {
    HomeContent(
        currentSeason = state.currentSeason,
        currentYear = state.currentYear,
        schedules = state.schedules,
        seasonAnime = state.seasonAnime,
        topAnime = state.topAnime,
        navToDetail = navToDetail
    )
}

@Composable
fun HomeContent(
    currentSeason: String,
    currentYear: Int,
    schedules: List<AnimePresentation.Data>,
    seasonAnime: List<AnimePresentation.Data>,
    topAnime: List<AnimePresentation.Data>,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .navigationBarsWithImePadding()
            .padding(bottom = 56.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            StatusBarSpacer()
            Header(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .testTag("Home Header"),
                title = "Home"
            )
        }
        PosterGridList(
            title = "Airing Today",
            items = schedules,
            navToDetail = { navToDetail(it) }
        )
        PosterGridList(
            title = "${currentSeason.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }} $currentYear",
            items = seasonAnime,
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
    items: List<AnimePresentation.Data>,
    navToDetail: (Int) -> Unit,
) {
    if(items.isEmpty()) {
        PosterGridListShimmerLoading()
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.SemiBold
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
    RisutoTheme {
        Box(
            Modifier.background(MaterialTheme.colors.background)
        ) {
            HomeContent(
                currentSeason = "Winter",
                currentYear = 2022,
                schedules = listOf(FakeItems.animeData, FakeItems.animeData, FakeItems.animeData, FakeItems.animeData),
                seasonAnime = listOf(FakeItems.animeData, FakeItems.animeData, FakeItems.animeData, FakeItems.animeData),
                topAnime = listOf(FakeItems.animeData, FakeItems.animeData, FakeItems.animeData, FakeItems.animeData),
                navToDetail = {}
            )
        }
    }
}

@Preview
@Composable
fun HomeContentEmptyPreview() {
    RisutoTheme {
        Box(
            Modifier.background(MaterialTheme.colors.background)
        ) {
            HomeContent(
                currentSeason = "Winter",
                currentYear = 2022,
                schedules = emptyList(),
                seasonAnime = emptyList(),
                topAnime = emptyList(),
                navToDetail = {}
            )
        }
    }
}


