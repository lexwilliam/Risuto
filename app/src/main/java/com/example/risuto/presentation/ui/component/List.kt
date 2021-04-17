package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.risuto.data.remote.model.detail.Character
import com.example.risuto.data.remote.model.detail.VoiceActor
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.util.generateFakeAnimeDetail
import com.example.risuto.presentation.util.generateFakeItem
import com.example.risuto.presentation.util.generateFakeItemList

@ExperimentalFoundationApi
@Composable
fun GridList(
    modifier: Modifier = Modifier,
    items: List<AnimeListPresentation>,
    navToDetail: (Int) -> Unit
) {
    if(items.isEmpty()) {
        LoadingScreen()
    } else {
        LazyVerticalGrid(
            modifier = modifier.padding(horizontal = 16.dp),
            cells = GridCells.Adaptive(minSize = 180.dp),
        ) {
            var count = 0
            items(items = items) { item ->
                count++
                if(count % 2 == 0){
                    MediumGrid(item = item, modifier = Modifier.padding(top = 16.dp, end = 0.dp), navToDetail = { navToDetail(it) })
                } else {
                    MediumGrid(item = item, modifier = Modifier.padding(top = 16.dp, end = 16.dp), navToDetail = { navToDetail(it) })
                }
            }
        }
    }
}

@Composable
fun ColumnList(
    modifier: Modifier = Modifier,
    items: List<AnimeListPresentation>,
    navToDetail: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = items){ item ->
            RowItem(item = item, navToDetail = { navToDetail(it) })
        }
    }
}


@Composable
fun HorizontalGridList(
    items: List<AnimeListPresentation>,
    navToDetail: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ){
        items(items = items){ item ->
            SmallGrid(
                item = item,
                navToDetail = { navToDetail(it) }
            )
        }
    }
}

@Preview
@Composable
fun ColumnListPreview() {
    ColumnList(items = generateFakeItemList(), navToDetail = {})
}

@ExperimentalFoundationApi
@Preview
@Composable
fun GridListPreview() {
    GridList(items = generateFakeItemList(), navToDetail = {})
}

@Preview
@Composable
fun HorizontalGridListPreview() {
    HorizontalGridList(items = generateFakeItemList(), navToDetail = {})
}

enum class ListType {
    ColumnList, GridList
}
