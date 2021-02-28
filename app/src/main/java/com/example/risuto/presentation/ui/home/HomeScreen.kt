package com.example.risuto.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.chun2maru.risutomvvm.presentation.model.SearchAnimePresentation

@Composable
fun HomeScreen(
    animes: List<SearchAnimePresentation>,
    executeAnimeSearch: (String) -> Unit
) {
    executeAnimeSearch("wonder egg priority")
    Column {
        LazyColumn(
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(items = animes) { anime ->
                AnimeGrid(anime = anime)
            }
        }
    }

}

@Composable
fun AnimeGrid(
    anime: SearchAnimePresentation
) {
    Row {
        Column {
            anime.title?.let { Text(it) }
            anime.members?.let { Text(it.toString()) }
            anime.score?.let { Text(it.toString()) }
        }
    }
}