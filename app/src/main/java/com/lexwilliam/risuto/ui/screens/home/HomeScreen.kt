package com.lexwilliam.risuto.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.ui.component.Header
import com.lexwilliam.risuto.ui.component.HorizontalGridList
import timber.log.Timber

@Composable
fun HomeScreen(
    state: HomeContract.State,
    navToDetail: (Int) -> Unit,
    navToLogin: () -> Unit
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
//    Timber.d("isToken = ${state.isTokenValid}")
//    if(state.isTokenValid != null) {
//        if(state.isTokenValid == false) {
//            navToLogin()
//        } else {
//
//        }
//    }

}

@Composable
fun HomeContent(
    airingTodayAnime: List<AnimePresentation>,
    currentSeasonAnime: List<AnimePresentation>,
    topAiringAnime: List<AnimePresentation>,
    topAnime: List<AnimePresentation>,
    topUpcomingAnime: List<AnimePresentation>,
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


