package com.example.risuto.presentation.ui.genre

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.ui.component.*

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
            onLoading = viewState.onLoading,
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
    onLoading: Boolean,
    onBackPressed: () -> Unit,
    navToDetail: (Int) -> Unit
) {
    if(onLoading) {
        LoadingScreen()
    } else {
        var listType by remember { mutableStateOf(ListType.GridList) }
        Column {
            TopAppBar(
                title = {
                    Text(text = genreList[genreId])
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                },
                actions = {
                    var listTypeBtn = Icons.Default.List
                    if (listType == ListType.GridList) listTypeBtn = Icons.Default.List
                    else listTypeBtn = Icons.Default.Menu
                    IconButton(onClick = {
                        if (listType == ListType.GridList) listType = ListType.ColumnList
                        else listType = ListType.GridList
                    }) {
                        Icon(
                            listTypeBtn,
                            contentDescription = null,
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 4.dp
            )
            if (listType == ListType.GridList) {
                GridList(
                    items = animeList,
                    navToDetail = { navToDetail(it) })
            } else {
                ColumnList(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    items = animeList,
                    navToDetail = { navToDetail(it) }
                )
            }
        }
    }
}