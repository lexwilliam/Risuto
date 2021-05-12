package com.example.risuto.presentation.ui.season

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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
//        if(isSeasonMenuShown) {
//            SeasonMenu(
//                archive = archive,
//                onSeasonSelected = {
//                    setSeason(it)
//                    onSeasonMenu(false)
//                }
//            )
//        }
        GridList(items = animes, navToDetail = { navToDetail(it)} )
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
            .padding(top = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        Header(
            modifier = Modifier
                .weight(2f)
                .wrapContentWidth(Alignment.Start)
                .clickable { onSeasonMenu() },
            title = seasonYearFormat(season, year)
        )
        Row(
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.End)
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

private fun onPreviousSeason(
    year: Int,
    season: String,
    setSeason: (String) -> Unit
) {
    val seasonIndex = allSeason.indexOf(season.capitalize(Locale.ROOT))
    val previousSeason: String
    if(seasonIndex != 0) {
        previousSeason = allSeason[seasonIndex - 1]
        setSeason("$previousSeason $year")
    } else {
        previousSeason = allSeason.last()
        setSeason("$previousSeason ${year-1}")
    }

}

private fun onNextSeason(
    year: Int,
    season: String,
    setSeason: (String) -> Unit
) {
    val seasonIndex = allSeason.indexOf(season.capitalize(Locale.ROOT))
    val nextSeason: String
    if(seasonIndex != allSeason.size-1) {
        nextSeason = allSeason[seasonIndex + 1]
        setSeason("$nextSeason ${year}")
    } else {
        nextSeason = allSeason.first()
        setSeason("$nextSeason ${year+1}")
    }
}

@Preview
@Composable
fun SeasonToolBarPreview() {
    SeasonToolBar(year = 2021, season = "spring", onSeasonMenu = {}, setSeason = {})
}

//@Composable
//fun SeasonMenu(
//    archive: List<Archive>,
//    onSeasonSelected: (String) -> Unit
//) {
//    var seasonText by remember { mutableStateOf("Season") }
//    var yearText by remember { mutableStateOf("Year") }
//    var isToggleSeason by remember { mutableStateOf(false) }
//    var isToggleYear by remember { mutableStateOf(false)}
//    val keyboardController = LocalSoftwareKeyboardController.current
//    Column {
//        Row(
//            modifier = Modifier
//                .padding(horizontal = 16.dp)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(2.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .height(32.dp)
//                    .clip(MaterialTheme.shapes.medium)
//                    .border(
//                        width = 1.dp,
//                        color = MaterialTheme.colors.secondary,
//                        shape = MaterialTheme.shapes.medium
//                    )
//                    .clickable { isToggleSeason = true },
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Row {
//                    Text(text = seasonText)
//                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
//                    DropdownMenu(
//                        expanded = isToggleSeason,
//                        onDismissRequest = { isToggleSeason = false }) {
//                        allSeason.forEach { season ->
//                            Log.d("TAG", season)
//                            DropdownMenuItem(onClick = {
//                                seasonText = season
//                                isToggleSeason = false
//                            }) {
//                                Text(text = season)
//                            }
//                        }
//                    }
//                }
//            }
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .height(32.dp)
//                    .clip(MaterialTheme.shapes.medium)
//                    .border(
//                        width = 1.dp,
//                        color = MaterialTheme.colors.secondary,
//                        shape = MaterialTheme.shapes.medium
//                    )
//                    .clickable { isToggleYear = true },
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Row {
//                    Text(text = yearText)
//                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
//                    DropdownMenu(
//                        expanded = isToggleYear,
//                        onDismissRequest = { isToggleYear = false }) {
//                        archive.forEach { archive ->
//                            Log.d("TAG", archive.year.toString())
//                            DropdownMenuItem(onClick = {
//                                yearText = archive.year.toString()
//                                isToggleYear = false
//                            }) {
//                                Text(text = archive.year.toString())
//                            }
//                        }
//                    }
//                }
//            }
////            BasicTextField(
////                modifier = Modifier.align(Alignment.CenterVertically),
////                value = yearText,
////                onValueChange = { yearText = it },
////                textStyle = MaterialTheme.typography.button,
////                singleLine = true,
////                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
////                keyboardActions = KeyboardActions(onDone = {
////                    keyboardController?.hideSoftwareKeyboard()
////                }),
////                decorationBox = {
////                    Column(
////                        modifier = Modifier
////                            .weight(1f)
////                            .height(32.dp)
////                            .clip(MaterialTheme.shapes.medium)
////                            .border(
////                                width = 1.dp,
////                                color = MaterialTheme.colors.secondary,
////                                shape = MaterialTheme.shapes.medium
////                            ),
////                        horizontalAlignment = Alignment.CenterHorizontally,
////                        verticalArrangement = Arrangement.Center
////                    ) {
////                        it()
////                    }
////                }
////            )
//        }
//        Column(modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onSeasonSelected("$seasonText $yearText") },
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(text = "Go", style = MaterialTheme.typography.button)
//        }
//    }
//}