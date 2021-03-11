package com.example.risuto.presentation.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.risuto.presentation.model.GridStylePresentation
import com.example.risuto.presentation.model.RowStylePresentation
import com.example.risuto.presentation.ui.component.*
import com.example.risuto.presentation.util.generateFakeGridItemList

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
//    HomeContent(
//        topAnime = listViewModel.topAiringAnime,
//        searchAnime = listViewModel.searchAnime,
//        onTopAnime = listViewModel::onTopAiringAnime,
//        onSearchAnime = listViewModel::onSearchAnime
//    )
}

@ExperimentalFoundationApi
@Composable
fun HomeContent(
    topAnime: List<GridStylePresentation>,
    searchAnime: List<RowStylePresentation>,
    onTopAnime: (Int, String) -> Unit,
    onSearchAnime: (String) -> Unit
) {
    onTopAnime(1, "airing")
    onSearchAnime("Wonder Egg Priority")
    Column(modifier = Modifier.fillMaxSize()) {
        HomeToolBar()
        TypeVerticalGridRow(title = "Top Airing", items = topAnime)
        RowItemList(items = searchAnime)
    }
}

@Composable
fun HomeToolBar() {
    Toolbar(title = "Home", navIcon = NavIcon.Menu, action = ActionIcon.Search)
}

@Composable
fun TypeVerticalGridRow(
    title: String,
    items: List<GridStylePresentation>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        VerticalGridRow(items = items)
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    HomeToolBar()
}

@Preview
@Composable
fun TypeVerticalGridListPreview(){
    TypeVerticalGridRow(title = "Top Airing", items = generateFakeGridItemList())
}
