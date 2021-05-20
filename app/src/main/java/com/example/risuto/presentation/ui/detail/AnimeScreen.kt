package com.example.risuto.presentation.ui.detail

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.domain.model.MyAnime
import com.example.risuto.presentation.model.AnimePresentation
import com.example.risuto.presentation.model.CharacterStaffPresentation
import com.example.risuto.presentation.ui.component.Chip
import com.example.risuto.presentation.ui.component.LoadingScreen
import com.example.risuto.presentation.ui.component.NetworkImage
import com.example.risuto.presentation.util.generateFakeAnimeDetail
import com.example.risuto.presentation.util.getGenre
import com.example.risuto.presentation.util.getJpnVoiceActor
import com.example.risuto.presentation.util.intToCurrency

@Composable
fun AnimeScreen(
    viewModel: AnimeViewModel = viewModel(),
    onBackPressed: () -> Unit,
    navToGenre: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    AnimeContent(
        animeDetail = viewState.animeDetail,
        animeStaff = viewState.animeStaff,
        onLoading = viewState.onLoading,
        onBackPressed = { onBackPressed() },
        navToGenre = navToGenre
    )
}

@Composable
fun AnimeContent(
    animeDetail: AnimePresentation,
    animeStaff: CharacterStaffPresentation,
    onLoading: Boolean,
    onBackPressed: () -> Unit,
    navToGenre: (Int) -> Unit
) {
    if(onLoading){
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
                },
                onBackPressed = { onBackPressed() }
            )
            AnimeDetail(animeDetail = animeDetail)
            AnimeGenre(animeDetail = animeDetail, navToGenre = { navToGenre(it) })
            AnimeRating(animeDetail = animeDetail)
            CharVoiceActorList(animeStaff = animeStaff)
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
                IconButton(onClick = { }) {
                    Icon(Icons.Outlined.Favorite, contentDescription = null, tint = MaterialTheme.colors.secondary)
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    )
}

@Composable
fun AnimeDetail(
    animeDetail: AnimePresentation
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
            imageUrl = animeDetail.image_url
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)

        ) {
            Text(text = animeDetail.title, style = MaterialTheme.typography.h6)
            Text(text = animeDetail.premiered+" | "+animeDetail.type+"("+animeDetail.episodes+")", style = MaterialTheme.typography.caption)
            Row {
                Text(text = "Studio: ", style = MaterialTheme.typography.button)
                animeDetail.studios.forEach {
                    it.name?.let { it1 -> Text(text = "$it1 ", style = MaterialTheme.typography.button) }
                }
            }
//            Text(text = genresToString(animeDetail.genres), style = MaterialTheme.typography.button)
            Divider()
            Text(text = animeDetail.synopsis, style = MaterialTheme.typography.body2, maxLines = 5, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
fun AnimeGenre(
    animeDetail: AnimePresentation,
    navToGenre: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Genre: ", style = MaterialTheme.typography.subtitle1)
        animeDetail.genres.forEach { genre ->
            genre.name?.let { Chip(modifier = Modifier.padding(end = 8.dp), text = it,
                onClick = { genre ->
                    navToGenre(getGenre(genre))
                })
            }
        }
    }
}

@Composable
fun AnimeRating(
    animeDetail: AnimePresentation
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
            Rating("Members", intToCurrency(animeDetail.members)),
            Rating("Rank", "#"+ intToCurrency(animeDetail.rank)),
            Rating("Popularity", "#"+ intToCurrency(animeDetail.popularity)),
            Rating("Favorited", intToCurrency(animeDetail.favorites)),
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

@Composable
fun CharVoiceActorList(
    animeStaff: CharacterStaffPresentation
) {
    Column(
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Text(text = "Voice Actor", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            animeStaff.characters.forEach {
                Column {
                    NetworkImage(
                        imageUrl = it.image_url,
                        modifier = Modifier
                            .size(width = 80.dp, height = 100.dp)
                    )
                    Surface(
                        modifier = Modifier.width(80.dp),
                        color = Color.Transparent
                    ) {
                        Text(
                            text = it.name + "\n",
                            maxLines = 2, overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.caption
                        )
                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                    NetworkImage(
                        imageUrl = getJpnVoiceActor(it.voice_actors).image_url,
                        modifier = Modifier
                            .size(width = 80.dp, height = 100.dp)
                    )
                    Surface(
                        modifier = Modifier.width(80.dp),
                        color = Color.Transparent
                    ) {
                        Text(
                            text = getJpnVoiceActor(it.voice_actors).name + "\n",
                            maxLines = 2, overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.caption
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun AnimeScreenPreview() {
    AnimeContent(
        animeDetail = generateFakeAnimeDetail(),
        animeStaff = CharacterStaffPresentation(),
        onLoading = false,
        onBackPressed = {},
        navToGenre = {}
    )
}
