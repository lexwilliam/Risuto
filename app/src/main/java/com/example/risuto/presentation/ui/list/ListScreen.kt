package com.example.risuto.presentation.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.chun2maru.risutomvvm.presentation.model.SearchAnimePresentation
import com.example.risuto.presentation.model.generateDummyAnime
import dev.chrisbanes.accompanist.glide.GlideImage
import java.text.NumberFormat
import java.util.*

@ExperimentalFoundationApi
@Composable
fun ListScreen(
    animes: List<SearchAnimePresentation>,
    executeAnimeSearch: (String) -> Unit
) {
    executeAnimeSearch("Konosuba")
    Column {
        ListToolBar()
        AnimeList(animes)
    }
}

@ExperimentalFoundationApi
@Composable
fun AnimeList(animes: List<SearchAnimePresentation>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 180.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        var count = 0
        items(items = animes) { anime ->
            count++
            if(count % 2 == 0){
                AnimeListItem(anime = anime, modifier = Modifier.padding(end = 0.dp))
            } else {
                AnimeListItem(anime = anime, modifier = Modifier.padding(end = 16.dp))
            }
        }
    }
}

@Composable
fun AnimeListItem(
    anime: SearchAnimePresentation,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp), true)
            .background(Color.White)
    ) {
        Box(modifier = Modifier
            .size(width = 180.dp, height = 216.dp)
            .background(Color.Gray)
        ) {
            anime.image_url?.let {
                GlideImage(
                    data = it.replace("\\\\", "/"),
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
        Column(modifier = Modifier.padding(8.dp)){
            anime.title?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row {
                Text(
                    text = anime.score.toString()
                )
                Text(text = " | ")
                val members = NumberFormat.getNumberInstance(Locale.ENGLISH).format(anime.members)
                Text(text = members + " members", maxLines = 1)
            }
        }
    }
}

@Composable
fun ListToolBar() {
    TopAppBar(
        title = { Text("Risuto") },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search Button")
            }
        }
    )
}



@ExperimentalFoundationApi
@Preview
@Composable
fun DummyListScreen() {
    Column {
        ListToolBar()
        DummyAnimeList(dummyModels = generateDummyAnime())
    }
}

@Preview
@Composable
fun ListToolBarPreview(){
    ListToolBar()
}