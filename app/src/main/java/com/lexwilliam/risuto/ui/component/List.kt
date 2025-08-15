package com.lexwilliam.risuto.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.model.UserProfilePresentation

@ExperimentalFoundationApi
@Composable
fun GridList(
    modifier: Modifier = Modifier,
    items: List<AnimePresentation.Data>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    navToDetail: (Int) -> Unit
) {
    if(items.isEmpty()) {
        GridListShimmerLoading()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { onRefresh() },
        ) {
            LazyVerticalGrid(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                columns = GridCells.Adaptive(minSize = 136.dp),
            ) {
                items(items = items) { item ->
                    MediumGrid(
                        modifier = Modifier.padding(bottom = 16.dp, end = 16.dp),
                        id = item.mal_id,
                        imageUrl = item.images.jpg.image_url,
                        title = item.title,
                        score = item.score,
                        members = item.members,
                        navToDetail = { navToDetail(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun HorizontalGridList(
    items: List<AnimePresentation.Data>,
    navToDetail: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp)
    ){
        items(items = items){ item ->
            SmallGrid(
                id = item.mal_id,
                imageUrl = item.images.jpg.image_url,
                title = item.title,
                navToDetail = { navToDetail(it) }
            )
        }
        item {
            Spacer(modifier = Modifier.padding(0.dp))
        }
    }
}

@Composable
fun FavoriteAnimeHorizontalGridList(
    items: List<UserProfilePresentation.Data.Favorites.Anime>,
    navToDetail: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp)
    ){
        items(items = items){ item ->
            SmallGrid(
                id = item.mal_id,
                imageUrl = item.images.jpg.image_url,
                title = item.title,
                navToDetail = { navToDetail(it) }
            )
        }
        item {
            Spacer(modifier = Modifier.padding(0.dp))
        }
    }
}

@Composable
fun FavoriteCharacterHorizontalGridList(
    items: List<UserProfilePresentation.Data.Favorites.Character>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp)
    ){
        items(items = items){ item ->
            SmallGrid(
                id = item.mal_id,
                imageUrl = item.images.jpg.image_url,
                title = item.name,
                navToDetail = {}
            )
        }
        item {
            Spacer(modifier = Modifier.padding(0.dp))
        }
    }
}

@Composable
fun FavoritePeopleHorizontalGridList(
    items: List<UserProfilePresentation.Data.Favorites.People>,
    navToPerson: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp)
    ){
        items(items = items){ item ->
            SmallGridPerson(
                id = item.mal_id,
                imageUrl = item.images.jpg.image_url,
                title = item.name,
                navToPerson = { navToPerson(it) }
            )
        }
        item {
            Spacer(modifier = Modifier.padding(0.dp))
        }
    }
}

@Composable
fun UpdatesHorizontalGridList(
    items: UserProfilePresentation.Data.Updates,
    navToDetail: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp)
    ){
        items(items = items.anime){ item ->
            SmallGrid(
                id = item.entry.mal_id,
                imageUrl = item.entry.images.jpg.image_url,
                title = item.entry.title,
                navToDetail = { navToDetail(it) }
            )
        }
        item {
            Spacer(modifier = Modifier.padding(0.dp))
        }
    }
}