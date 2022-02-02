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
import androidx.paging.compose.items
import com.lexwilliam.domain.model.remote.search.SearchAnime
import com.lexwilliam.risutov2.mapper.AnimeMapper
import com.lexwilliam.risutov2.mapper.AnimeMapperImpl
import com.lexwilliam.risutov2.mapper.CommonMapperImpl
import com.lexwilliam.risutov2.model.AnimePresentation
import com.lexwilliam.risutov2.ui.component.LoadingScreen
import com.lexwilliam.risutov2.ui.component.RowItem
import com.lexwilliam.risutov2.util.bottomNavGap
import com.lexwilliam.risutov2.util.genreList
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalFoundationApi
@Composable
fun GenreScreen(
    state: GenreContract.State,
    onBackPressed: () -> Unit,
    navToDetail: (Int) -> Unit
) {
    if(state.isLoading) {
        LoadingScreen()
    } else {
        GenreContent(
            animeList = state.animes!!,
            genreId = state.genreId,
            onBackPressed = onBackPressed,
            navToDetail = navToDetail
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun GenreContent(
    animeList: Flow<PagingData<AnimePresentation>>,
    genreId: Int,
    onBackPressed: () -> Unit,
    navToDetail: (Int) -> Unit
) {
    val lazyAnimeList = animeList.collectAsLazyPagingItems()
    Column(modifier = Modifier.padding(bottom = bottomNavGap)) {
        TopAppBar(
            title = {
                Text(text = if (genreId > 0) genreList[genreId-1] else "unknown")
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
                RowItem(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp), item = anime!!, navToDetail = { navToDetail(anime.mal_id!!) })
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