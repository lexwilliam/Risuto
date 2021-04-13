package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.risuto.presentation.model.QuerySearch

private val filterAnimeTypeList= arrayListOf("TV", "OVA", "Movie", "Special", "ONA", "Music")
private val filterAnimeStatusList = arrayListOf("Airing", "Completed", "Upcoming")
private val filterAnimeGenreList = arrayListOf("Action", "Adventure", "Cars", "Comedy", "Dementia", "Demons", "Mystery", "Drama", "Ecchi", "Fantasy", "Game", "Hentai", "Historical", "Horror", "Kids", "Magic", "Parody", "Samurai", "Romance", "School", "Sci Fi", "Shoujo", "Shoujo Ai", "Space", "Sports", "Super Power", "Vampire", "Yaoi", "Yuri", "Harem", "Slice of Life", "Supernatural", "Military", "Police", "Psychological", "Thriller", "Seinen", "Josei")

@Composable
fun FilterPreviewRow() {

}

@Composable
fun FilterList() {

}

@Composable
fun FilterGenre(
    onClick: (Int) -> Unit
) {
    Column {
        Text(text = "Genre", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
        ChipGroup(
            texts = filterAnimeGenreList,
            onClick = {
                onClick(filterAnimeGenreList.indexOf(it) + 1)
            }
        )
    }
}