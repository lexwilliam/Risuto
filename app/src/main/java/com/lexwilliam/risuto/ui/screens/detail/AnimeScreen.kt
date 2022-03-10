package com.lexwilliam.risuto.ui.screens.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.flowlayout.FlowRow
import com.lexwilliam.risuto.model.AnimeDetailPresentation
import com.lexwilliam.risuto.model.WatchStatusPresentation
import com.lexwilliam.risuto.ui.component.Chip
import com.lexwilliam.risuto.ui.component.LoadingScreen
import com.lexwilliam.risuto.ui.component.NetworkImage
import com.lexwilliam.risuto.ui.theme.RisutoTheme
import com.lexwilliam.risuto.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AnimeScreen(
    state: AnimeContract.State,
    onEventSent: (AnimeContract.Event) -> Unit,
    onBackPressed: () -> Unit,
    navToSearchWithGenre: (Int) -> Unit
) {
    var isDone by remember { mutableStateOf(false) }
    if(!state.isLoading && !isDone) {
        onEventSent(AnimeContract.Event.InsertAnimeHistory(state.animeDetail))
        isDone = true
    }
    if(state.isLoading) {
        LoadingScreen()
    } else {
        AnimeContent(
            animeDetail = state.animeDetail,
            onBackPressed = { onBackPressed() },
            navToSearchWithGenre = navToSearchWithGenre
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun AnimeContent(
    animeDetail: AnimeDetailPresentation,
    onBackPressed: () -> Unit,
    navToSearchWithGenre: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AnimeToolBar(
            onAddPressed = {

            },
            onBackPressed = { onBackPressed() }
        )
        AnimeDetail(animeDetail = animeDetail)
        AnimeGenre(animeDetail = animeDetail, navToSearchWithGenre = { navToSearchWithGenre(it) })
        AnimeRating(animeDetail = animeDetail)
//            CharVoiceActorList(animeStaff = state.characterStaff)
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
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            NetworkImage(
                modifier = Modifier
                    .fillMaxWidth(),
                imageUrl = animeDetail.pictures.first().large
            )
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colors.background
                            )
                        )
                    )
                    .align(Alignment.BottomCenter)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NetworkImage(
                modifier = Modifier
                    .size(width = 120.dp, height = 200.dp)
                    .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.small, true),
                imageUrl = animeDetail.main_picture.medium
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Spacer(Modifier.padding(8.dp))
                Text(text = animeDetail.title, style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
                Text(text = animeDetail.studios.first().name, style = MaterialTheme.typography.caption)
                Text(text = "${animeDetail.num_episodes} episodes ")
                Text(text = animeDetail.num_list_users.toString())
                Text(text = animeDetail.popularity.toString())
            }
        }
    }

}

@Composable
fun AnimeGenre(
    animeDetail: AnimeDetailPresentation,
    navToSearchWithGenre: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Genre: ", style = MaterialTheme.typography.subtitle1)
        animeDetail.genres.forEach { genre ->
            Chip(modifier = Modifier.padding(end = 8.dp), text = genre.name,
                onClick = {
                    navToSearchWithGenre(getGenre(it))
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
            Rating("Score", animeDetail.mean.toString()),
            Rating("Members", intToCurrency(animeDetail.num_list_users)),
            Rating("Rank", "#"+ intToCurrency(animeDetail.rank)),
            Rating("Popularity", "#"+ intToCurrency(animeDetail.popularity)),
            Rating("Rated", animeDetail.rating)
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

//@Composable
//fun CharVoiceActorList(
//    animeStaff: CharacterStaffPresentation
//) {
//    Column {
//        Text(modifier = Modifier.padding(start = 16.dp), text = "Voice Actor", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(2.dp),
//            contentPadding = PaddingValues(start = 16.dp)
//        ) {
//            items(items = animeStaff.characters!!) { item ->
//                Column {
//                    NetworkImage(
//                        imageUrl = item.image_url,
//                        modifier = Modifier
//                            .size(width = 80.dp, height = 100.dp)
//                    )
//                    Surface(
//                        modifier = Modifier.width(80.dp),
//                        color = Color.Transparent
//                    ) {
//                        Text(
//                            text = item.name + "\n",
//                            maxLines = 2, overflow = TextOverflow.Ellipsis,
//                            style = MaterialTheme.typography.caption
//                        )
//                    }
//                    Spacer(modifier = Modifier.padding(2.dp))
//                    NetworkImage(
//                        imageUrl = getJpnVoiceActor(
//                            item.voice_actors
//                        ).image_url,
//                        modifier = Modifier
//                            .size(width = 80.dp, height = 100.dp)
//                    )
//                    Surface(
//                        modifier = Modifier.width(80.dp),
//                        color = Color.Transparent
//                    ) {
//                        Text(
//                            text = getJpnVoiceActor(
//                                item.voice_actors
//                            ).name + "\n",
//                            maxLines = 2, overflow = TextOverflow.Ellipsis,
//                            style = MaterialTheme.typography.caption
//                        )
//                    }
//
//                }
//            }
//        }
//    }
//}

@Preview
@Composable
fun AnimeScreenPreview() {
    RisutoTheme {
        Box(
            Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize()
        ) {
            AnimeDetail(animeDetail = FakeItems.animeDetail)
        }
    }
}