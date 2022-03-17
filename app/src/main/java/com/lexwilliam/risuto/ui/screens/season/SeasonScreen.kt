package com.lexwilliam.risuto.ui.screens.season

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.lexwilliam.risuto.R
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.model.SeasonListPresentation
import com.lexwilliam.risuto.ui.component.*
import com.lexwilliam.risuto.ui.screens.detail.AnimeContract
import com.lexwilliam.risuto.ui.theme.RisutoTheme
import com.lexwilliam.risuto.util.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun SeasonScreen(
    state: SeasonContract.State,
    onEventSent: (SeasonContract.Event) -> Unit,
    navToDetail: (Int) -> Unit
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    if(state.isLoading) {
        LoadingScreen()
    } else {
        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = 0.dp,
            sheetBackgroundColor = MaterialTheme.colors.background,
            sheetElevation = 16.dp,
            sheetContent = {
                SeasonMenu(
                    season = state.season,
                    year = state.year,
                    seasonList = state.seasonList,
                    onEventSent = { onEventSent(it) },
                    onDoneClicked = {
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }
                )
            }
        ) {
            SeasonContent(
                year = state.year,
                season = state.season,
                animes = state.seasonAnime,
                isRefreshing = state.isRefreshing,
                onEventSent = { onEventSent(it) },
                navToDetail = { navToDetail(it) },
                onFilterClicked = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    }
                }
            )
        }
    }
}

@Composable
fun SeasonMenu(
    season: String,
    year: Int,
    seasonList: SeasonListPresentation,
    onEventSent: (SeasonContract.Event) -> Unit,
    onDoneClicked: () -> Unit
) {
    var season by remember { mutableStateOf(season) }
    var year by remember { mutableStateOf(year) }

    Column(
        modifier = Modifier
            .navigationBarsWithImePadding()
            .padding(start = 16.dp, end = 16.dp, bottom = 56.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Season Filter",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Season", style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
        ChipGroup(texts = allSeason, selectedText = season.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }, onSelectedTextChanged = { season = allSeason[it] })
        Text(text = "Year", style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            enabled = (season != "" && year != -1),
            onClick = {
                onEventSent(SeasonContract.Event.SetSeason(season, year))
                onDoneClicked()
            }
        ) {
            Text(text = "Done", style = MaterialTheme.typography.button)
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun SeasonContent(
    year: Int,
    season: String,
    animes: List<AnimePresentation.Data>,
    isRefreshing: Boolean,
    onEventSent: (SeasonContract.Event) -> Unit,
    navToDetail: (Int) -> Unit,
    onFilterClicked: () -> Unit
) {
    Column(modifier = Modifier
        .navigationBarsWithImePadding()
        .padding(bottom = 56.dp)) {
        StatusBarSpacer()
        if(year != -1 && season != "") {
            SeasonToolBar(
                year = year,
                season = season,
                onEventSent = { onEventSent(it) },
                onFilterClicked = { onFilterClicked() }
            )
        }
        GridList(
            items = animes,
            isRefreshing = isRefreshing,
            onRefresh = { onEventSent(SeasonContract.Event.RefreshList(season, year))},
            navToDetail = { navToDetail(it) }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun SeasonToolBar(
    year: Int,
    season: String,
    onEventSent: (SeasonContract.Event) -> Unit,
    onFilterClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Header(
            modifier = Modifier
                .weight(2f)
                .wrapContentWidth(Alignment.Start),
            title = seasonYearFormat(season, year)
        )
        Row(
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.End)
        ) {
            val previous = onPreviousSeason(year, season)
            val next = onNextSeason(year, season)
            IconButton(
                modifier = Modifier
                    .size(32.dp),
                onClick = { onEventSent(
                    SeasonContract.Event.SetSeason(
                        previous.season,
                        previous.year
                    )
                ) }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
            IconButton(
                modifier = Modifier
                    .size(32.dp),
                onClick = { onEventSent(SeasonContract.Event.SetSeason(next.season, next.year)) }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null
                )
            }
            IconButton(
                modifier = Modifier
                    .size(32.dp),
                onClick = { onFilterClicked() }
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            }
        }
    }
}

data class SeasonAndYear(
    val season: String,
    val year: Int
)

val allSeason = listOf("Winter", "Spring", "Summer", "Fall")

private fun onPreviousSeason(
    year: Int,
    season: String
): SeasonAndYear {
    val seasonIndex = allSeason.indexOf(season.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
    val previousSeason: String
    return if(seasonIndex != 0) {
        previousSeason = allSeason[seasonIndex - 1]
        SeasonAndYear(previousSeason, year)
    } else {
        previousSeason = allSeason.last()
        SeasonAndYear(previousSeason, year - 1)
    }

}

private fun onNextSeason(
    year: Int,
    season: String
): SeasonAndYear {
    val seasonIndex = allSeason.indexOf(season.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
    val nextSeason: String
    return if(seasonIndex != allSeason.size-1) {
        nextSeason = allSeason[seasonIndex + 1]
        SeasonAndYear(nextSeason, year)
    } else {
        nextSeason = allSeason.first()
        SeasonAndYear(nextSeason, year + 1)
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class,
    ExperimentalFoundationApi::class
)
@Preview
@Composable
fun SeasonPreview() {
    RisutoTheme {
        Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
            SeasonContent(
                year = 2022,
                season = "Winter",
                animes = listOf(FakeItems.animeData, FakeItems.animeData, FakeItems.animeData, FakeItems.animeData, FakeItems.animeData, FakeItems.animeData),
                isRefreshing = false,
                onEventSent = {},
                navToDetail = {},
                onFilterClicked = {}
            )
        }
    }
}