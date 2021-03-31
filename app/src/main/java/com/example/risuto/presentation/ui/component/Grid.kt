package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.risuto.presentation.model.GridStylePresentation
import com.example.risuto.presentation.util.generateFakeGridItem
import com.example.risuto.presentation.util.generateFakeGridItemList

//@ExperimentalFoundationApi
//@Composable
//fun BigGridList(items: List<GridStylePresentation>){
//    LazyVerticalGrid(
//        cells = GridCells.Adaptive(minSize = 180.dp),
//        modifier = Modifier
//            .padding(16.dp)
//    ) {
//        var count = 0
//        items(items = items) { item ->
//            count++
//            if(count % 2 == 0){
//                Grid(item = item, modifier = Modifier.padding(bottom = 16.dp, end = 0.dp))
//            } else {
//                Grid(item = item, modifier = Modifier.padding(bottom = 16.dp, end = 16.dp))
//            }
//        }
//    }
//}

@Composable
fun VerticalGridRow(
    items: List<GridStylePresentation>,
    navToDetail: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ){
        items(items = items){ item ->
            PosterGrid(
                item = item,
                navToDetail = { navToDetail(it) }
            )
        }
    }
}

@Composable
fun PosterGrid(
    modifier: Modifier = Modifier,
    item: GridStylePresentation,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier
            .wrapContentSize()
            .width(110.dp)
            .clickable {
                navToDetail(item.mal_id)
            }
    ) {
        NetworkImage(imageUrl = item.image_url, width = 110.dp, height = 180.dp)
        Text(text = item.title + "\n",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

//@Preview
//@Composable
//fun BigGridPreview(){
//    PosterGrid(item = generateFakeGridItem())
//}

//@ExperimentalFoundationApi
//@Preview
//@Composable
//fun BigGridListPreview(){
//    BigGridList(items = generateFakeGridItemList())
//}

//@Preview
//@Composable
//fun VerticalGridRowPreview(){
//    VerticalGridRow(items = generateFakeGridItemList())
//}