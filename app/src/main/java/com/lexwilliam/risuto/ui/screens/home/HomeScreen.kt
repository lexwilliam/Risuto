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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lexwilliam.risuto.model.remote.AnimePresentation
import com.lexwilliam.risuto.ui.component.Header
import com.lexwilliam.risuto.ui.component.HorizontalGridListV4
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
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, bottom = 64.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, end = 16.dp)
        ) {
            Header(
                modifier = Modifier
                    .weight(2f)
                    .wrapContentWidth(Alignment.Start),
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
        Box(modifier = Modifier
            .size(240.dp)
            .background(Color.Transparent))
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            HorizontalGridListV4(
                items = items,
                navToDetail = { navToDetail(it) }
            )
        }
    }
}

//@Preview
//@Composable
//fun HomeContentPreview() {
//    HomeContent(
//        currentSeasonAnime = generateFakeItemList(),
//        topAiringAnime = generateFakeItemList(),
//        topAnime = generateFakeItemList(),
//        topUpcomingAnime = generateFakeItemList(),
//        navToDetail = {}
//    )
//}


