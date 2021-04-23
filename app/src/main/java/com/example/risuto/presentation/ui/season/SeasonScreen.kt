package com.example.risuto.presentation.ui.season

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.data.remote.model.detail.Archive
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.ui.component.GridList
import com.example.risuto.presentation.ui.component.Header
import com.example.risuto.presentation.util.seasonYearFormat

@ExperimentalFoundationApi
@Composable
fun SeasonScreen(
    viewModel: SeasonViewModel = viewModel(),
    navToDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    var isSeasonMenuShown by remember { mutableStateOf(false) }

    SeasonContent(
        year = viewState.year,
        season = viewState.season,
        archive = viewState.seasonArchive,
        animes = viewState.seasonAnimes,
        setSeason = viewModel::setSeason,
        isSeasonMenuShown = isSeasonMenuShown,
        onSeasonMenu = { isSeasonMenuShown = it },
        navToDetail = { navToDetail(it) }
    )
}

@ExperimentalFoundationApi
@Composable
fun SeasonContent(
    year: Int,
    season: String,
    archive: List<Archive>,
    animes: List<AnimeListPresentation>,
    setSeason: (String) -> Unit,
    isSeasonMenuShown: Boolean,
    onSeasonMenu: (Boolean) -> Unit,
    navToDetail: (Int) -> Unit
) {
    Column {
        SeasonToolBar(year = year, season = season, onSeasonMenu = { onSeasonMenu(true) })
        GridList(items = animes, navToDetail = { navToDetail(it)} )
    }
    if(isSeasonMenuShown) {
        SeasonMenu(
            archive = archive,
            onSeasonSelected = {
                setSeason(it)
                onSeasonMenu(false)
            }
        )
    }
}

@Composable
fun SeasonToolBar(
    year: Int,
    season: String,
    onSeasonMenu: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Header(title = "Seasonal Anime")
        Row(
            modifier = Modifier
                .clickable { onSeasonMenu() },
            verticalAlignment = Alignment.CenterVertically
        ) {
           Text(text = seasonYearFormat(season, year), style = MaterialTheme.typography.button)
           Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }
    }
}

@Composable
fun SeasonMenu(
    archive: List<Archive>,
    onSeasonSelected: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {
        items(items = archive) { item ->
            for(i in 0..3) {
                val title = seasonYearFormat(item.season[i], item.year)
                Text(
                    modifier = Modifier
                        .clickable { onSeasonSelected(title) },
                    text = title,
                    style = MaterialTheme.typography.subtitle2
                )
                Divider()
            }
        }
    }
}