package com.lexwilliam.risuto.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.ui.component.Header
import com.lexwilliam.risuto.ui.component.HorizontalGridList
import com.lexwilliam.risuto.ui.component.PosterGridListShimmerLoading
import com.lexwilliam.risuto.ui.component.StatusBarSpacer
import java.util.Locale

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

