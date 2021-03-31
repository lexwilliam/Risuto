package com.example.risuto.presentation.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.GridStylePresentation
import com.example.risuto.presentation.ui.component.NavSearchBar
import com.example.risuto.presentation.ui.component.VerticalGridRow

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navToDetail: (Int) -> Unit,
    navToSearch: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()

    HomeContent(
        topAiringAnime = viewState.topAiringAnime,
        topAnime = viewState.topAnime,
        topUpcomingAnime = viewState.topUpcomingAnime,
        navToDetail = navToDetail,
        navToSearch = navToSearch
    )
}

@Composable
fun HomeContent(
    topAiringAnime: List<GridStylePresentation>,
    topAnime: List<GridStylePresentation>,
    topUpcomingAnime: List<GridStylePresentation>,
    navToDetail: (Int) -> Unit,
    navToSearch: () -> Unit
) {
    val state = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(state),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NavSearchBar(navToSearch)
        PosterGridStyle(
            title = "Top Airing",
            items = topAiringAnime,
            navToDetail = { navToDetail(it) }
        )
        PosterGridStyle(
            title = "Top Upcoming",
            items = topUpcomingAnime,
            navToDetail = { navToDetail(it) }
        )
        PosterGridStyle(
            title = "Top Anime",
            items = topAnime,
            navToDetail = { navToDetail(it) }
        )
    }
}

@Composable
fun PosterGridStyle(
    title: String,
    items: List<GridStylePresentation>,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        VerticalGridRow(
            items = items,
            navToDetail = { navToDetail(it) }
        )
    }
}

//@Preview
//@Composable
//fun ToolbarPreview() {
//    HomeToolBar(navController = )
//}

//@Preview
//@Composable
//fun TypeVerticalGridListPreview(){
//    PosterGridStyle(title = "Top Airing", items = generateFakeGridItemList())
//}
