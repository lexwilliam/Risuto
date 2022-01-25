package com.lexwilliam.risutov2.ui.genre

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.lexwilliam.risutov2.model.AnimeListPresentation
import com.example.risuto.presentation.ui.component.*
import com.lexwilliam.risutov2.util.bottomNavGap
import com.lexwilliam.risutov2.util.genreList
import com.lexwilliam.risutov2.ui.component.RowItem
import kotlinx.coroutines.flow.Flow

@ExperimentalFoundationApi
@Composable
fun GenreScreen(
    viewModel: GenreViewModel = viewModel(),
    onBackPressed: () -> Unit,
    navToDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    GenreContent(
        animeList = viewModel.animes,
        genreId = viewState.genreIndex!!,
        onBackPressed = onBackPressed,
        navToDetail = navToDetail
    )
}

@ExperimentalFoundationApi
@Composable
fun GenreContent(
    animeList: Flow<PagingData<AnimeListPresentation>>,
    genreId: Int,
    onBackPressed: () -> Unit,
    navToDetail: (Int) -> Unit
) {
    val lazyAnimeList = animeList.collectAsLazyPagingItems()
    Column(modifier = Modifier.padding(bottom = _root_ide_package_.com.lexwilliam.risutov2.util.bottomNavGap)) {
        TopAppBar(
            title = {
                Text(text = _root_ide_package_.com.lexwilliam.risutov2.util.genreList[genreId-1])
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
            backgroundColor = MaterialTheme.colors.background,
            elevation = 4.dp
        )
        LazyColumn {
            items(lazyAnimeList) { anime ->
                RowItem(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp), item = anime!!, navToDetail = { navToDetail(anime.mal_id) })
            }

            lazyAnimeList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> { }
                    loadState.append is LoadState.Loading -> { }
                    loadState.refresh is LoadState.Error -> {
                        val e = lazyAnimeList.loadState.refresh as LoadState.Error
                        item {
                            Text(
                                text = e.error.localizedMessage!!,
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val e = lazyAnimeList.loadState.append as LoadState.Error
                        item {
                            Text(
                                text = e.error.localizedMessage!!
                            )
                        }
                    }
                }
            }
        }
    }
}