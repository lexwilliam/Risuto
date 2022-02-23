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
import com.lexwilliam.risuto.model.AnimeListPresentation
import com.lexwilliam.risuto.model.remote.AnimePresentation
import com.lexwilliam.risuto.ui.component.Header
import com.lexwilliam.risuto.ui.component.HorizontalGridList
import com.lexwilliam.risuto.ui.component.HorizontalGridListV4

@Composable
fun HomeScreen(
    state: HomeContract.State,
    onEventSent: (HomeContract.Event) -> Unit,
    navToDetail: (Int) -> Unit
) {
    HomeContent(
        airingTodayAnime = state.airingTodayAnime,
        currentSeasonAnime = state.seasonAnime,
        topAiringAnime = state.topAiringAnime,
        topAnime = state.topAnime,
        topUpcomingAnime = state.topUpcomingAnime,
        username = state.username,
        navToDetail = navToDetail
    )
}

@Composable
fun HomeContent(
    airingTodayAnime: List<AnimeListPresentation>,
    currentSeasonAnime: List<AnimeListPresentation>,
    topAiringAnime: List<AnimeListPresentation>,
    topAnime: List<AnimePresentation.Data>,
    topUpcomingAnime: List<AnimeListPresentation>,
    username: String,
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
            Text(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End),
                text = username
            )
        }
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
        PosterGridListV4(
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

@Composable
fun PosterGridListV4(
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


