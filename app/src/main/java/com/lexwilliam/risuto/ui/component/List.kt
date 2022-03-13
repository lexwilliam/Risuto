package com.lexwilliam.risuto.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.model.ShortAnimePresentation

@ExperimentalFoundationApi
@Composable
fun GridList(
    modifier: Modifier = Modifier,
    items: List<AnimePresentation.Data>,
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
                MediumGrid(
                    modifier = Modifier.padding(bottom = 16.dp, end = 16.dp),
                    id = item.mal_id,
                    imageUrl = item.images.jpg.image_url,
                    title = item.title,
                    score = item.score,
                    members = item.members,
                    navToDetail = { navToDetail(it) }
                )
            }
        }
    }
}

@Composable
fun HorizontalGridList(
    items: List<AnimePresentation.Data>,
    navToDetail: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp)
    ){
        items(items = items){ item ->
            SmallGrid(
                id = item.mal_id,
                imageUrl = item.images.jpg.image_url,
                title = item.title,
                navToDetail = { navToDetail(it) }
            )
        }
        item {
            Spacer(modifier = Modifier.padding(0.dp))
        }
    }
}
//
//@Preview
//@Composable
//fun ColumnListPreview() {
//    ColumnList(items = generateFakeItemList(), navToDetail = {})
//}
//
//@ExperimentalFoundationApi
//@Preview
//@Composable
//fun GridListPreview() {
//    GridList(items = generateFakeItemList(), navToDetail = {})
//}
//
//@Preview
//@Composable
//fun HorizontalGridListPreview() {
//    HorizontalGridList(items = generateFakeItemList(), navToDetail = {})
//}
