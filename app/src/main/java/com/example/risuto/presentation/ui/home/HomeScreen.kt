package com.example.risuto.presentation.ui.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.ui.component.HorizontalGridList
import com.example.risuto.presentation.ui.component.NavSearchBar
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
        isLoading = viewState.isLoading,
        navToDetail = navToDetail,
        navToSearch = navToSearch
    )
}

@Composable
fun HomeContent(
    currentSeasonAnime: List<AnimeListPresentation>,
    topAiringAnime: List<AnimeListPresentation>,
    topAnime: List<AnimeListPresentation>,
    topUpcomingAnime: List<AnimeListPresentation>,
    isLoading: Boolean,
    navToDetail: (Int) -> Unit,
    navToSearch: () -> Unit
) {
    Log.d("TAG", "ON LOADING$isLoading")
    val state = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(state)
            .padding(bottom = 64.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NavSearchBar(navToSearch = { navToSearch() })
        if(!isLoading) {
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
        } else {
            Text(text = "LOADING", style = MaterialTheme.typography.h2)
        }
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


