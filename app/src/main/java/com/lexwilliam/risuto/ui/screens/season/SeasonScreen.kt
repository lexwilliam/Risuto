package com.lexwilliam.risuto.ui.screens.season

import androidx.compose.foundation.*
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
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.ui.component.GridList
import com.lexwilliam.risuto.ui.component.Header
import com.lexwilliam.risuto.ui.component.ImeAvoidingBox
import com.lexwilliam.risuto.ui.component.StatusBarSpacer
import com.lexwilliam.risuto.ui.theme.RisutoTheme
import com.lexwilliam.risuto.util.*
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
    SeasonContent(
        year = state.year,
        season = state.season,
        animes = state.seasonAnime,
        onEventSent = { onEventSent(it) },
        navToDetail = { navToDetail(it) }
    )
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun SeasonContent(
    year: Int,
    season: String,
    animes: List<AnimePresentation.Data>,
    onEventSent: (SeasonContract.Event) -> Unit,
    navToDetail: (Int) -> Unit
) {
    Timber.d("year : $year")
    Timber.d("season: $season")
    Column(modifier = Modifier.navigationBarsWithImePadding().padding(bottom = 56.dp)) {
        StatusBarSpacer()
        if(year != -1 && season != "") {
            SeasonToolBar(
                year = year,
                season = season,
                onEventSent = { onEventSent(it) }
            )
        }
        GridList(items = animes, navToDetail = { navToDetail(it)} )
    }
}

@ExperimentalMaterialApi
@Composable
fun SeasonToolBar(
    year: Int,
    season: String,
    onEventSent: (SeasonContract.Event) -> Unit,
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
                onEventSent = {},
                navToDetail = {}
            )
        }
    }
}