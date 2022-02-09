package com.lexwilliam.risuto.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.ui.component.Header
import com.lexwilliam.risuto.ui.component.HorizontalGridList
import com.lexwilliam.risuto.ui.component.LoadingScreen
import com.lexwilliam.risuto.util.getCurrentSeason
import com.lexwilliam.risuto.util.getCurrentYear
import java.util.*

@Composable
fun HomeScreen(
    state: HomeContract.State,
    navToDetail: (Int) -> Unit
) {
    HomeContent(
        airingTodayAnime = state.airingTodayAnime,
        currentSeasonAnime = state.seasonAnime,
        topAiringAnime = state.topAiringAnime,
        topAnime = state.topAnime,
        topUpcomingAnime = state.topUpcomingAnime,
        navToDetail = navToDetail
    )
}

@Composable
fun HomeContent(
    airingTodayAnime: List<AnimePresentation>,
    currentSeasonAnime: List<AnimePresentation>,
    topAiringAnime: List<AnimePresentation>,
    topAnime: List<AnimePresentation>,
    topUpcomingAnime: List<AnimePresentation>,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, bottom = 64.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Header(title = "Home", modifier = Modifier.padding(top = 24.dp))
        PosterGridList(
            title = "Airing Today",
            items = airingTodayAnime,
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
    items: List<AnimePresentation>,
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
            HorizontalGridList(
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


