package com.example.risuto.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

private val typeList= arrayListOf("TV", "OVA", "Movie", "Special", "ONA", "Music")
private val statusList = arrayListOf("Airing", "Completed", "Upcoming")
val genreList = arrayListOf("Action", "Adventure", "Cars", "Comedy", "Dementia", "Demons", "Mystery", "Drama", "Ecchi", "Fantasy", "Game", "Hentai", "Historical", "Horror", "Kids", "Magic", "Parody", "Samurai", "Romance", "School", "Sci Fi", "Shoujo", "Shoujo Ai", "Space", "Sports", "Super Power", "Vampire", "Yaoi", "Yuri", "Harem", "Slice of Life", "Supernatural", "Military", "Police", "Psychological", "Thriller", "Seinen", "Josei")
val genreSearchList = arrayListOf("Action", "Adventure", "Cars", "Comedy", "Dementia", "Demons", "Mystery", "Drama", "Ecchi", "Fantasy", "Game", "Hentai", "Historical", "Horror", "Kids", "Magic", "Martial Arts", "Sci-fi", "Music", "Parody", "Samurai", "Romance", "School", "Sci-Fi", "Shoujo", "Shoujo Ai", "Shounen", "Shounen Ai", "Space", "Sport", "Super Power", "Vampire", "Yaoi", "Yuri", "Harem", "Slice of Life", "Supernatural", "Military")
private val orderByList = arrayListOf("Title", "Score", "Type", "Members", "Episodes", "Rating")
private val sortList = arrayListOf("asc", "desc")

@Composable
fun FilterPreviewRow() {

}

@Composable
fun FilterList() {

}

@Composable
fun FilterGenre(
    navToGenre: (Int) -> Unit
) {
    Column {
        Text(text = "Genre", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
        ChipGroup(
            texts = genreSearchList,
            onClick = {
                navToGenre(genreSearchList.indexOf(it) + 1)
            }
        )
    }
}