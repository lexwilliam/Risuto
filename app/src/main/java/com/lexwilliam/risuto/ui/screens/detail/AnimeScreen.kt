package com.lexwilliam.risuto.ui.screens.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.lexwilliam.risuto.model.AnimeCharactersPresentation
import com.lexwilliam.risuto.model.AnimeDetailPresentation
import com.lexwilliam.risuto.ui.component.LoadingScreen
import com.lexwilliam.risuto.ui.component.NetworkImage
import com.lexwilliam.risuto.ui.theme.RisutoTheme
import com.lexwilliam.risuto.util.FakeItems
import com.lexwilliam.risuto.util.getInitialAnimeDetails
import com.lexwilliam.risuto.util.getJpnVoiceActor
import com.lexwilliam.risuto.util.intToCurrency
import java.util.*

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
    if(state.animeDetail == getInitialAnimeDetails() && state.characters == emptyList<AnimeCharactersPresentation.Data>()) {
        LoadingScreen()
    } else {
        AnimeContent(
            animeDetail = state.animeDetail,
            characters = state.characters,
            onBackPressed = { onBackPressed() },
            navToSearchWithGenre = navToSearchWithGenre
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun AnimeContent(
    animeDetail: AnimeDetailPresentation,
    characters: List<AnimeCharactersPresentation.Data>,
    onBackPressed: () -> Unit,
    navToSearchWithGenre: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
//        AnimeToolBar(
//            onAddPressed = {
//
//            },
//            onBackPressed = { onBackPressed() }
//        )
        AnimeDetail(animeDetail = animeDetail, navToSearchWithGenre = { navToSearchWithGenre(it) })
        AnimeSynopsis(synopsis = animeDetail.synopsis)
        CharVoiceActorList(characters = characters )
        Spacer(modifier = Modifier.padding(4.dp))
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
    animeDetail: AnimeDetailPresentation,
    navToSearchWithGenre: (Int) -> Unit
) {
    Column(
        modifier = Modifier.padding(start = 40.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            NetworkImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .clip(RoundedCornerShape(bottomStart = 32.dp)),
                imageUrl = animeDetail.main_picture.large
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            FlowRow(
                mainAxisSpacing = 16.dp,
                crossAxisSpacing = 8.dp
            ) {
                animeDetail.genres.forEach { genre ->
                    Box(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(color = MaterialTheme.colors.primary)
                            .wrapContentSize()
                            .clickable { navToSearchWithGenre(genre.id) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                            text = genre.name,
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = animeDetail.title,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.SemiBold
            )
        }
        Row(modifier = Modifier
            .padding(end = 16.dp)
            .fillMaxWidth()) {
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "RANK", style = MaterialTheme.typography.overline, fontWeight = FontWeight.Bold)
                Text(text = "#${animeDetail.rank}", style = MaterialTheme.typography.h5, fontWeight = FontWeight.SemiBold)
            }
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "RATING", style = MaterialTheme.typography.overline, fontWeight = FontWeight.Bold)
                Row {
                    Text(text = animeDetail.mean.toString(), style = MaterialTheme.typography.h5, fontWeight = FontWeight.SemiBold)
                    Text(modifier = Modifier.align(Alignment.Bottom), text = "/10", style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.SemiBold)
                }
            }
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "MEMBERS", style = MaterialTheme.typography.overline, fontWeight = FontWeight.Bold)
                Text(text = intToCurrency(animeDetail.num_list_users), style = MaterialTheme.typography.h5, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun AnimeSynopsis(
    synopsis: String
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    var finalText by remember { mutableStateOf(synopsis) }

    val textLayoutResult = textLayoutResultState.value
    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded -> {
                finalText = "$synopsis Show Less"
            }
            !isExpanded && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(5 - 1)
                val showMoreString = "... Show More"
                val adjustedText = synopsis
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile { it == ' ' || it == '.' }

                finalText = "$adjustedText$showMoreString"

                isClickable = true
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(start = 40.dp, end = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = "Synopsis",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.SemiBold
        )

        Column(
            modifier = Modifier
                .clickable(enabled = isClickable) { isExpanded = !isExpanded }
        ) {
            Text(
                text = synopsis,
                style = MaterialTheme.typography.body1,
                maxLines = if (isExpanded) Int.MAX_VALUE else 5,
                onTextLayout = { textLayoutResultState.value = it },
                modifier = Modifier
                    .animateContentSize(),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if(isExpanded) {
                    Icon(modifier = Modifier.size(32.dp), imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
                } else {
                    Icon(modifier = Modifier.size(32.dp), imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                }
            }
        }
    }
}

@Composable
fun CharVoiceActorList(
    characters: List<AnimeCharactersPresentation.Data>
) {
    Column {
        Text(
            modifier = Modifier
                .padding(start = 40.dp, bottom = 8.dp),
            text = "Voice Actor",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.SemiBold
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(start = 40.dp)
        ) {
            items(items = characters) { item ->
                Column(
                    modifier = Modifier
                        .width(80.dp)
                        .wrapContentHeight()
                ) {
                    Column(
                        modifier = Modifier
                            .shadow(4.dp, MaterialTheme.shapes.small, clip = true)
                            .background(color = MaterialTheme.colors.background)
                    ) {
                        NetworkImage(
                            imageUrl = item.character.images.jpg.image_url,
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.small)
                                .size(width = 80.dp, height = 100.dp)
                        )
                        Text(
                            modifier = Modifier.padding(2.dp),
                            text = item.character.name + '\n',
                            maxLines = 2, overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.caption,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Column(
                        modifier = Modifier
                            .shadow(4.dp, MaterialTheme.shapes.small, clip = true)
                            .background(color = MaterialTheme.colors.background)
                    ) {
                        NetworkImage(
                            imageUrl = getJpnVoiceActor(item.voice_actors).person.images.jpg.image_url,
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.small)
                                .size(width = 80.dp, height = 100.dp)
                        )
                        Text(
                            modifier = Modifier.padding(2.dp),
                            text = getJpnVoiceActor(item.voice_actors).person.name + '\n',
                            maxLines = 2, overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.caption,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(0.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun AnimeScreenPreview() {
    RisutoTheme {
        Column(
            Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            AnimeContent(
                animeDetail = FakeItems.animeDetail,
                characters = listOf(FakeItems.character, FakeItems.character, FakeItems.character, FakeItems.character, FakeItems.character, FakeItems.character),
                onBackPressed = {},
                navToSearchWithGenre = {}
            )
        }
    }
}