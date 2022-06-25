package com.lexwilliam.risuto.ui.screens.person

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import com.lexwilliam.risuto.model.PersonPresentation
import com.lexwilliam.risuto.ui.component.LoadingScreen
import com.lexwilliam.risuto.ui.component.NetworkImage
import com.lexwilliam.risuto.ui.screens.detail.DetailSubtitle

@Composable
fun PersonScreen(
    state: PersonContract.State,
    onBackPressed: () -> Unit,
    navToDetail: (Int) -> Unit
) {
    if(state.isLoading) {
        LoadingScreen()
    } else {
        val scrollState = rememberLazyListState()
        Box() {
            PersonContent(
                person = state.person,
                navToDetail = navToDetail
            )
            PersonToolBar(
                onBackPressed = onBackPressed,
                scrollState = scrollState
            )
        }
    }
}

@Composable
fun PersonContent(
    person: PersonPresentation.Data,
    navToDetail: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .navigationBarsWithImePadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { PersonPoster(image_url = person.images.jpg.image_url, name = person.name) }
        item { PersonAbout(about = person.about) }
        item { PersonAnime(animes = person.anime, navToDetail = navToDetail) }
        item { PersonManga(mangas = person.manga)}
        item { PersonVoice(voices = person.voices, navToDetail = navToDetail) }
        item { Spacer(modifier = Modifier.padding(4.dp)) }
    }
}

@Composable
fun PersonToolBar(
    onBackPressed: () -> Unit,
    scrollState: LazyListState
) {
    var backgroundColor by remember { mutableStateOf(Color.Transparent) }
    if(scrollState.firstVisibleItemIndex > 0)
        backgroundColor = MaterialTheme.colors.background
    else
        backgroundColor = Color.Transparent
    TopAppBar(
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars,
            applyBottom = false,
        ),
        backgroundColor = backgroundColor,
        elevation = if(backgroundColor == Color.Transparent) 0.dp else 8.dp,
        title = { Text("") },
        navigationIcon = {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colors.background)
                    .clickable { onBackPressed() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = MaterialTheme.colors.onBackground)
            }
        }
    )
}

@Composable
fun PersonPoster(
    image_url: String,
    name: String
) {
    val nameSp = name.split(" ")
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {
        NetworkImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .graphicsLayer { alpha = 0.99f }
                .drawWithContent {
                    val colors = listOf(
                        Color.Black,
                        Color.Transparent
                    )
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(colors),
                        blendMode = BlendMode.DstIn
                    )
                },
            imageUrl = image_url
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            nameSp.forEach { name ->
                Text(
                    text = name,
                    style = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}

@Composable
fun PersonSubtitle(
    title: String
) {
    Text(
        modifier = Modifier
            .padding(start = 16.dp, bottom = 8.dp),
        text = title,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun PersonAbout(
    about: String
) {
    Column(
    ) {
        PersonSubtitle(title = "About")
        Text(modifier = Modifier.padding(16.dp), text = about, style = MaterialTheme.typography.body1)
    }
}

@Composable
fun PersonAnime(
    animes: List<PersonPresentation.Data.Anime>,
    navToDetail: (Int) -> Unit
) {
    Column {
        PersonSubtitle(title = "Anime")
        if(animes.isNotEmpty()) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(start = 16.dp)
            ) {
                items(items = animes) { item ->
                    Column(
                        modifier = Modifier
                            .width(80.dp)
                            .wrapContentHeight()
                    ) {
                        Column(
                            modifier = Modifier
                                .shadow(4.dp, MaterialTheme.shapes.small, clip = true)
                                .background(color = MaterialTheme.colors.background)
                                .clickable { navToDetail(item.anime.mal_id) }
                        ) {
                            NetworkImage(
                                imageUrl = item.anime.images.jpg.image_url,
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.small)
                                    .size(width = 80.dp, height = 100.dp)
                            )
                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = item.anime.title + '\n',
                                maxLines = 2, overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.caption,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = item.position + '\n',
                                maxLines = 2, overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.caption
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PersonManga(
    mangas: List<PersonPresentation.Data.Manga>
) {
    if(mangas.isNotEmpty()) {
        Column {
            PersonSubtitle(title = "Manga")
            if(mangas.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(start = 16.dp)
                ) {
                    items(items = mangas) { item ->
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
                                    imageUrl = item.manga.images.jpg.image_url,
                                    modifier = Modifier
                                        .clip(MaterialTheme.shapes.small)
                                        .size(width = 80.dp, height = 100.dp)
                                )
                                Text(
                                    modifier = Modifier.padding(2.dp),
                                    text = item.manga.title + '\n',
                                    maxLines = 2, overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    modifier = Modifier.padding(2.dp),
                                    text = item.position + '\n',
                                    maxLines = 2, overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.caption
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PersonVoice(
    voices: List<PersonPresentation.Data.Voice>,
    navToDetail: (Int) -> Unit
) {
    if(voices.isNotEmpty()) {
        Column {
            PersonSubtitle(title = "Voice")
            if(voices.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(start = 16.dp)
                ) {
                    items(items = voices) { item ->
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
                                    .clickable {
                                        navToDetail(item.anime.mal_id)
                                    }
                            ) {
                                NetworkImage(
                                    imageUrl = item.anime.images.jpg.image_url,
                                    modifier = Modifier
                                        .clip(MaterialTheme.shapes.small)
                                        .size(width = 80.dp, height = 100.dp)
                                )
                                Text(
                                    modifier = Modifier.padding(2.dp),
                                    text = item.anime.title + '\n',
                                    maxLines = 2, overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

