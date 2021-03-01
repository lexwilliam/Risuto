package com.example.risuto.presentation.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.risuto.presentation.model.DummyAnimeModel
import com.example.risuto.presentation.model.generateDummyAnime
import java.text.NumberFormat
import java.util.*

@ExperimentalFoundationApi
@Composable
fun DummyAnimeList(dummyModels: List<DummyAnimeModel>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 180.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        var count = 0
        items(items = dummyModels) { item ->
            count++
            if(count % 2 == 0){
                DummyAnime(dummyModel = item, modifier = Modifier.padding(end = 0.dp))
            } else {
                DummyAnime(dummyModel = item, modifier = Modifier.padding(end = 16.dp))
            }
        }
    }
}

@Composable
fun DummyAnime(dummyModel: DummyAnimeModel, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp), true)
            .background(color = Color.White)
    ) {
        Box(modifier = Modifier
            .size(width = 180.dp, height = 216.dp)
            .clip(RectangleShape)
            .background(Color.Yellow)
        )
        Column(modifier = Modifier.padding(8.dp)){
            Text(
                text = dummyModel.name,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Row() {
                Text(
                    text = dummyModel.score.toString()
                )
                Text(text = " | ")
                val members = NumberFormat.getNumberInstance(Locale.ENGLISH).format(dummyModel.members)
                Text(text = members + " members", maxLines = 1)
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun DummyAnimeListPreview(){
    DummyAnimeList(dummyModels = generateDummyAnime())
}

@Preview
@Composable
fun DummyAnimePreview(){
    DummyAnime(dummyModel = DummyAnimeModel("Wonder Egg Priority", 9.6f, 72132))
}

@Preview
@Composable
fun TestColumn(){
    Column(modifier = Modifier
        .size(180.dp, 290.dp)
        .shadow(2.dp, RoundedCornerShape(8.dp))) {
    }
}


