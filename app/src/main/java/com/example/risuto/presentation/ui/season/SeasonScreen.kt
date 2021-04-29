package com.example.risuto.presentation.ui.season

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.data.remote.model.detail.Archive
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.ui.component.GridList
import com.example.risuto.presentation.ui.component.Header
import com.example.risuto.presentation.util.allSeason
import com.example.risuto.presentation.util.seasonYearFormat
import java.util.*

@ExperimentalComposeUiApi
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

@ExperimentalComposeUiApi
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
        SeasonToolBar(
            year = year,
            season = season,
            setSeason = { setSeason(it) },
            onSeasonMenu = { onSeasonMenu(true) }
        )
        GridList(items = animes, navToDetail = { navToDetail(it)} )
    }
    if (isSeasonMenuShown) {
        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SeasonMenu(
                archive = archive,
                onSeasonSelected = {
                    setSeason(it)
                    onSeasonMenu(false)
                }
            )
        }
    }
}

@Composable
fun SeasonToolBar(
    year: Int,
    season: String,
    setSeason: (String) -> Unit,
    onSeasonMenu: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Header(
            modifier = Modifier
                .wrapContentWidth(Alignment.Start)
                .clickable { onSeasonMenu() },
            title = seasonYearFormat(season, year)
        )
        Spacer(modifier = Modifier.padding(32.dp))
        Row(
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier
                    .size(32.dp),
                onClick = { onPreviousSeason(year, season) { setSeason(it) } }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
            IconButton(
                modifier = Modifier
                    .size(32.dp),
                onClick = { onNextSeason(year, season) { setSeason(it) } }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun SeasonMenu(
    archive: List<Archive>,
    onSeasonSelected: (String) -> Unit
) {
    var seasonText by remember { mutableStateOf("") }
    var yearText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier = Modifier
            .size(240.dp)
            .shadow(4.dp, shape = MaterialTheme.shapes.large, clip = true)
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = seasonText,
                onValueChange = { seasonText = it },
                label = { Text("Season") },
                singleLine = true
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = yearText,
                onValueChange = { yearText = it },
                label = { Text("Year") },
                singleLine = true
            )
            Text(
                modifier = Modifier
                    .wrapContentWidth(Alignment.End)
                    .clickable {
                        onSeasonSelected("$seasonText $yearText")
                        keyboardController?.hideSoftwareKeyboard()
                    },
                text = "Filter",
                style = MaterialTheme.typography.button
            )
        }
    }
}

private fun onPreviousSeason(
    year: Int,
    season: String,
    setSeason: (String) -> Unit
) {
    val seasonIndex = allSeason.indexOf(season.capitalize(Locale.ROOT))
    Log.d("TAG", "$seasonIndex $season")
    val previousSeason: String
    if(seasonIndex != 0) {
        previousSeason = allSeason[seasonIndex - 1]
        Log.d("TAG", "$seasonIndex $season")
        setSeason("$previousSeason $year")
    } else {
        previousSeason = allSeason.last()
        Log.d("TAG", "$seasonIndex $season")
        setSeason("$previousSeason ${year-1}")
    }

}

private fun onNextSeason(
    year: Int,
    season: String,
    setSeason: (String) -> Unit
) {
    val seasonIndex = allSeason.indexOf(season.capitalize(Locale.ROOT))
    Log.d("TAG", "$seasonIndex $season")
    val nextSeason: String
    if(seasonIndex != allSeason.size-1) {
        nextSeason = allSeason[seasonIndex + 1]
        Log.d("TAG", "$seasonIndex $season")
        setSeason("$nextSeason ${year}")
    } else {
        nextSeason = allSeason.first()
        Log.d("TAG", "$seasonIndex $season")
        setSeason("$nextSeason ${year+1}")
    }
}

@Preview
@Composable
fun SeasonToolBarPreview() {
    SeasonToolBar(year = 2021, season = "spring", onSeasonMenu = {}, setSeason = {})
}