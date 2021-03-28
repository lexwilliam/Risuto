package com.example.risuto.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.GridStylePresentation
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
        Modifier.verticalScroll(state)
    ) {
        HomeToolBar(navToSearch)
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
fun HomeToolBar(
    navToSearch: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.padding(4.dp)
        ) {
            IconButton(onClick = {  }) {
                Icon(Icons.Filled.Menu, contentDescription = null, modifier = Modifier.size(32.dp))
            }
        }
        Text(
            text = "Home",
            modifier = Modifier.padding(horizontal = 100.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Surface(
            modifier = Modifier.padding(4.dp)
        ) {
            IconButton(onClick = {
                navToSearch()
            }) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
fun PosterGridStyle(
    title: String,
    items: List<GridStylePresentation>,
    navToDetail: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp),
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
