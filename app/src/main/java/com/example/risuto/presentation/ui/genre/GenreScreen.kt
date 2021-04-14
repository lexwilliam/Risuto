package com.example.risuto.presentation.ui.genre

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.ui.component.GridList
import com.example.risuto.presentation.ui.component.genreList
import com.example.risuto.presentation.ui.component.genreSearchList

@ExperimentalFoundationApi
@Composable
fun GenreScreen(
    viewModel: GenreViewModel = viewModel(),
    onBackPressed: () -> Unit,
    navToDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    viewState.genreIndex?.let {
        GenreContent(
        animeList = viewState.genreAnimes,
        genreId = it,
        onBackPressed = onBackPressed,
        navToDetail = navToDetail
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun GenreContent(
    animeList: List<AnimeListPresentation>,
    genreId: Int,
    onBackPressed: () -> Unit,
    navToDetail: (Int) -> Unit
) {
    Column {
        TopAppBar(
            title = {
                Text(text = genreSearchList[genreId])
            },
            navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = MaterialTheme.colors.secondary)
                }
            },
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp
        )
        GridList(modifier = Modifier.padding(horizontal = 16.dp),items = animeList, navToDetail = { navToDetail(it) })
    }
}