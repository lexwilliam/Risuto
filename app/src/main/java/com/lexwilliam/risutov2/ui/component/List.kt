package com.lexwilliam.risutov2.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lexwilliam.risutov2.model.AnimeListPresentation
import com.lexwilliam.risutov2.util.generateFakeItemList

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
            modifier = modifier
                .fillMaxSize()
                .padding(start = 16.dp),
            cells = GridCells.Adaptive(minSize = 136.dp),
        ) {
            items(items = items) { item ->
                MediumGrid(item = item, modifier = Modifier.padding(top = 16.dp, end = 16.dp), navToDetail = { navToDetail(it) })
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
        modifier = modifier.padding(top = 8.dp)
    ) {
        items(items = items){ item ->
            RowItem(modifier = Modifier.padding(vertical = 8.dp), item = item, navToDetail = { navToDetail(it) })
            Divider()
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
