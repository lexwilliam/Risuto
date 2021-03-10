package com.example.risuto.presentation.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chun2maru.risutomvvm.presentation.viewmodel.ListViewModel
import com.example.risuto.presentation.model.custom.GridStylePresentation
import com.example.risuto.presentation.model.custom.RowStylePresentation
import com.example.risuto.presentation.ui.component.RowItemList
import com.example.risuto.presentation.ui.component.VerticalGridRow
import com.example.risuto.presentation.util.generateFakeGridItemList

@ExperimentalFoundationApi
@Composable
fun HomeScreen(listViewModel: ListViewModel) {
    HomeContent(
        topAnime = listViewModel.topAiringAnime,
        searchAnime = listViewModel.searchAnime,
        onTopAnime = listViewModel::onTopAiringAnime,
        onSearchAnime = listViewModel::onSearchAnime
    )
}

@ExperimentalFoundationApi
@Composable
fun HomeContent(
    topAnime: List<GridStylePresentation>,
    searchAnime: List<RowStylePresentation>,
    onTopAnime: (String, Int, String) -> Unit,
    onSearchAnime: (String) -> Unit
) {
    onTopAnime("anime", 1, "airing")
    onSearchAnime("Wonder Egg Priority")
    Column(modifier = Modifier.fillMaxSize()) {
        HomeToolBar()
        TypeVerticalGridRow(title = "Top Airing", items = topAnime)
        RowItemList(items = searchAnime)
    }
}

@Composable
fun HomeToolBar() {
    TopAppBar(
        title = {
            Text(
                text = "Search",
                modifier = Modifier
                    .clickable {

                    }
            )
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }

        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Person, contentDescription = null)
            }
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        contentColor = MaterialTheme.colors.onSecondary
    )
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
            fontSize = 24.sp
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
