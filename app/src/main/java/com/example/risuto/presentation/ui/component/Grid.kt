package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.request.RequestOptions
import com.example.risuto.presentation.model.custom.GridStylePresentation
import com.example.risuto.presentation.util.generateFakeGridItem
import com.example.risuto.presentation.util.generateFakeGridItemList
import com.example.risuto.presentation.util.intToCurrency
import dev.chrisbanes.accompanist.glide.GlideImage

@ExperimentalFoundationApi
@Composable
fun BigGridList(items: List<GridStylePresentation>){
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 180.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        var count = 0
        items(items = items) { item ->
            count++
            if(count % 2 == 0){
                Grid(item = item, modifier = Modifier.padding(bottom = 16.dp, end = 0.dp))
            } else {
                Grid(item = item, modifier = Modifier.padding(bottom = 16.dp, end = 16.dp))
            }
        }
    }
}

@Composable
fun VerticalGridRow(items: List<GridStylePresentation>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.wrapContentSize().padding(16.dp)
    ){
        items(items = items){ item ->
            Grid(
                item = item,
                imageWidth = 100,
                imageHeight = 144,
                textUnit = 12.sp,
                fontWeight = FontWeight.Normal,
                isDetailShown = false)
        }
    }
}

@Composable
fun Grid(
    modifier: Modifier = Modifier,
    item: GridStylePresentation,
    imageWidth: Int = 180,
    imageHeight: Int = 216,
    textUnit: TextUnit = 16.sp,
    maxLines: Int = 2,
    fontWeight: FontWeight = FontWeight.Bold,
    isDetailShown: Boolean = true
) {
    Column(
        modifier
            .wrapContentSize()
            .background(Color.White)
            .width(imageWidth.dp)
    ) {
        Box(modifier = Modifier
            .size(width = imageWidth.dp, height = imageHeight.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Gray)
        ) {
            if (item.image_url.isNotEmpty()) {
                GlideImage(
                    data = item.image_url.replace("\\\\", "/"),
                    contentDescription = "Anime Picture",
                    fadeIn = true,
                    modifier = Modifier.fillMaxSize(),
                    requestBuilder = {
                        val options = RequestOptions()
                        options
                            .centerCrop()
                        apply(options)
                    }
                )
            }
        }
        Column(modifier = Modifier.padding(4.dp)){
            Text(
                text = item.title + "\n",
                fontSize = textUnit,
                fontWeight = fontWeight,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis
            )
            if(isDetailShown == true) {
                Column {
                    Row {
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Text(text = item.score.toString() + "  " + item.type + " (" + item.episodes.toString() + " )")
                    }
                    Row {
                        Icon(Icons.Filled.Person, contentDescription = null, tint = Color.DarkGray)
                        Text(text = intToCurrency(item.members), maxLines = 1)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BigGridPreview(){
    Grid(item = generateFakeGridItem(), modifier = Modifier.size(180.dp, 300.dp))
}

@ExperimentalFoundationApi
@Preview
@Composable
fun BigGridListPreview(){
    BigGridList(items = generateFakeGridItemList())
}

@Preview
@Composable
fun VerticalGridRowPreview(){
    VerticalGridRow(items = generateFakeGridItemList())
}