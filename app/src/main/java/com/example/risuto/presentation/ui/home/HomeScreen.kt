package com.example.risuto.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.GridStylePresentation
import com.example.risuto.presentation.ui.component.*
import com.example.risuto.presentation.util.generateFakeGridItemList

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val viewState by viewModel.state.collectAsState()
    HomeContent(
        topAiringAnime = viewState.topAiringAnime,
        topAnime = viewState.topAnime,
        topUpcomingAnime = viewState.topUpcomingAnime
    )
}

@Composable
fun HomeContent(
    topAiringAnime: List<GridStylePresentation>,
    topAnime: List<GridStylePresentation>,
    topUpcomingAnime: List<GridStylePresentation>
) {
    val state = rememberScrollState()
    Column(
        Modifier.verticalScroll(state)
    ) {
        HomeToolBar()
        PosterGridStyle(title = "Top Airing", items = topAiringAnime)
        PosterGridStyle(title = "Top Upcoming", items = topUpcomingAnime)
        PosterGridStyle(title = "Top Anime", items = topAnime)
    }
}

@Composable
fun HomeToolBar() {
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
            modifier = Modifier.padding(horizontal = 120.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Surface(
            modifier = Modifier.padding(4.dp)
        ) {
            IconButton(onClick = { }) {
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
    items: List<GridStylePresentation>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp),
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        VerticalGridRow(items = items)
    }
}

//@Preview
//@Composable
//fun ToolbarPreview() {
//    HomeToolBar(navController = )
//}

@Preview
@Composable
fun TypeVerticalGridListPreview(){
    PosterGridStyle(title = "Top Airing", items = generateFakeGridItemList())
}
