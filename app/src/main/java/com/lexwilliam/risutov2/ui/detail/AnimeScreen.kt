package com.lexwilliam.risutov2.ui.detail

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.risutov2.model.AnimePresentation
import com.lexwilliam.risutov2.model.detail.AnimeDetailPresentation
import com.lexwilliam.risutov2.model.detail.CharacterStaffPresentation
import com.lexwilliam.risutov2.model.local.MyAnimePresentation
import com.lexwilliam.risutov2.model.local.WatchStatusPresentation
import com.lexwilliam.risutov2.ui.component.Chip
import com.lexwilliam.risutov2.ui.component.LoadingScreen
import com.lexwilliam.risutov2.ui.component.NetworkImage
import com.lexwilliam.risutov2.util.*
import com.lexwilliam.risutov2.util.intToCurrency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalMaterialApi
@Composable
fun AnimeScreen(
    state: AnimeContract.State,
    onEventSent: (AnimeContract.Event) -> Unit,
    onBackPressed: () -> Unit,
    navToGenre: (Int) -> Unit
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        modifier = Modifier.background(MaterialTheme.colors.background),
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            MyAnimeMenu(
                onEventSent = { onEventSent(it) },
                state = state,
                onDoneClicked = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            )
        }
    ) {
        AnimeContent(
            state = state,
            onBackPressed = { onBackPressed() },
            navToGenre = navToGenre,
            bottomSheetState = bottomSheetScaffoldState,
            coroutineScope = coroutineScope
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun AnimeContent(
    state: AnimeContract.State,
    onBackPressed: () -> Unit,
    navToGenre: (Int) -> Unit,
    bottomSheetState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope
) {
    if(state.isLoading) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AnimeToolBar(
                onAddPressed = {
                    coroutineScope.launch {
                        bottomSheetState.bottomSheetState.expand()
                    }
                },
                onBackPressed = { onBackPressed() }
            )
            AnimeDetail(animeDetail = state.animeDetail)
            AnimeGenre(animeDetail = state.animeDetail, navToGenre = { navToGenre(it) })
            AnimeRating(animeDetail = state.animeDetail)
            CharVoiceActorList(animeStaff = state.characterStaff)
        }
    }
}

@Composable
fun MyAnimeMenu(
    onDoneClicked: () -> Unit,
    state: AnimeContract.State,
    onEventSent: (AnimeContract.Event) -> Unit
) {
    var score by remember { mutableStateOf(-1) }
    var watchStateText by remember { mutableStateOf("Plan To Watch")}
    var watchState by remember { mutableStateOf(WatchStatusPresentation.PlanToWatch) }
    var expandedWatchStatus by remember { mutableStateOf(false) }
    var expandedScore by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(bottom = 64.dp, top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(40.dp)
                    .background(Color.LightGray)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { expandedScore = true },
                contentAlignment = Alignment.Center
            ) {
                if(score != -1) {
                    Text(
                        text = "$score/10",
                        style = MaterialTheme.typography.subtitle1
                    )
                } else {
                    Text(
                        text = "Score",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                DropdownMenu(expanded = expandedScore, onDismissRequest = { expandedScore = false }) {
                    for(i in 10 downTo 1) {
                        DropdownMenuItem(onClick = {
                            score = i
                            expandedScore = false
                        }) {
                            Text(i.toString())
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(40.dp)
                    .background(Color.LightGray)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { expandedWatchStatus = true },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = watchStateText,
                    style = MaterialTheme.typography.subtitle1
                )
                DropdownMenu(expanded = expandedWatchStatus, onDismissRequest = { expandedWatchStatus = false }) {
                    watchStatusList.forEach {
                        DropdownMenuItem(onClick = {
                            watchState = it
                            watchStateText =
                                watchStatusToString(
                                    it
                                )
                            expandedWatchStatus = false
                        }) {
                            Text(
                                watchStatusToString(
                                    it
                                )
                            )
                        }
                    }
                }
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                onEventSent(
                    AnimeContract.Event.InsertMyAnime(
                        anime = MyAnimePresentation(
                            mal_id = state.animeDetail.mal_id!!,
                            title = state.animeDetail.title!!,
                            image_url = state.animeDetail.image_url!!,
                            myScore = score,
                            watchStatus = watchState
                        )
                    )
                )
                onDoneClicked()
            }) {
            Text("Done")
        }
    }
}

@Composable
fun AnimeToolBar(
    onAddPressed: () -> Unit,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = { Text("") },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = MaterialTheme.colors.secondary)
            }
        },
        actions = {
            Row {
                IconButton(onClick = { onAddPressed() }) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = MaterialTheme.colors.secondary)
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    )
}

@Composable
fun AnimeDetail(
    animeDetail: AnimeDetailPresentation
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NetworkImage(
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, true),
            imageUrl = animeDetail.image_url!!
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)

        ) {
            Text(text = animeDetail.title!!, style = MaterialTheme.typography.h6)
            Text(text = animeDetail.premiered+" | "+animeDetail.type+"("+animeDetail.episodes+")", style = MaterialTheme.typography.caption)
            Row {
                Text(text = "Studio: ", style = MaterialTheme.typography.button)
                animeDetail.studios?.forEach {
                    it.name?.let { it1 -> Text(text = "$it1 ", style = MaterialTheme.typography.button) }
                }
            }
//            Text(text = genresToString(animeDetail.genres), style = MaterialTheme.typography.button)
            Divider()
            Text(text = animeDetail.synopsis!!, style = MaterialTheme.typography.body2, maxLines = 5, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
fun AnimeGenre(
    animeDetail: AnimeDetailPresentation,
    navToGenre: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Genre: ", style = MaterialTheme.typography.subtitle1)
        animeDetail.genres?.forEach { genre ->
            Chip(modifier = Modifier.padding(end = 8.dp), text = genre.name,
                onClick = {
                    navToGenre(getGenre(it))
                })
        }
    }
}

@Composable
fun AnimeRating(
    animeDetail: AnimeDetailPresentation
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier.horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        data class Rating(val string: String, val int: String)
        val ratings = listOf(
            Rating("Score", animeDetail.score.toString()),
            Rating("Members", intToCurrency(animeDetail.members!!)),
            Rating("Rank", "#"+ intToCurrency(animeDetail.rank!!)),
            Rating("Popularity", "#"+ intToCurrency(animeDetail.popularity!!)),
            Rating("Favorited", intToCurrency(animeDetail.favorites!!)),
            Rating("Rated", animeDetail.rating!!)
        )
        ratings.forEach { rating ->
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(text = rating.string, style = MaterialTheme.typography.caption)
                Text(text = rating.int, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
            }
            Divider(
                modifier = Modifier
                    .padding(start = 1.dp)
                    .width(2.dp)
                    .height(48.dp)
            )
        }
    }
}

@Composable
fun CharVoiceActorList(
    animeStaff: CharacterStaffPresentation
) {
    Column {
        Text(modifier = Modifier.padding(start = 16.dp), text = "Voice Actor", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            contentPadding = PaddingValues(start = 16.dp)
        ) {
            items(items = animeStaff.characters!!) { item ->
                Column {
                    NetworkImage(
                        imageUrl = item.image_url,
                        modifier = Modifier
                            .size(width = 80.dp, height = 100.dp)
                    )
                    Surface(
                        modifier = Modifier.width(80.dp),
                        color = Color.Transparent
                    ) {
                        Text(
                            text = item.name + "\n",
                            maxLines = 2, overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.caption
                        )
                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                    NetworkImage(
                        imageUrl = getJpnVoiceActor(
                            item.voice_actors
                        ).image_url,
                        modifier = Modifier
                            .size(width = 80.dp, height = 100.dp)
                    )
                    Surface(
                        modifier = Modifier.width(80.dp),
                        color = Color.Transparent
                    ) {
                        Text(
                            text = getJpnVoiceActor(
                                item.voice_actors
                            ).name + "\n",
                            maxLines = 2, overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.caption
                        )
                    }

                }
            }
        }
    }
}

//@Preview
//@Composable
//fun AnimeScreenPreview() {
//    AnimeContent(
//        animeDetail = generateFakeAnimeDetail(),
//        animeStaff = CharacterStaffPresentation(),
//        onLoading = false,
//        onBackPressed = {},
//        navToGenre = {}
//    )
//}
