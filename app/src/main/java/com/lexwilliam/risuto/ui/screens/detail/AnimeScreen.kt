package com.lexwilliam.risuto.ui.screens.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import com.lexwilliam.risuto.model.WatchStatusPresentation
import com.lexwilliam.risuto.ui.component.*
import com.lexwilliam.risuto.ui.theme.RisutoTheme
import com.lexwilliam.risuto.util.*
import com.lexwilliam.risuto.util.intToCurrency
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnimeScreen(
    state: AnimeContract.State,
    onEventSent: (AnimeContract.Event) -> Unit,
    onBackPressed: () -> Unit,
    navToSearchWithGenre: (Int) -> Unit,
    navToDetail: (Int) -> Unit
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
            navToSearchWithGenre = navToSearchWithGenre,
            navToDetail = navToDetail
        )
        Box {
            AnimeToolbar(
                status = state.animeDetail.my_list_status,
                onAddPressed = { },
                onBackPressed = { onBackPressed() }
            )
        }
    }
}

@Composable
fun AnimeContent(
    animeDetail: AnimeDetailPresentation,
    characters: List<AnimeCharactersPresentation.Data>,
    navToSearchWithGenre: (Int) -> Unit,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AnimeDetail(animeDetail = animeDetail, navToSearchWithGenre = { navToSearchWithGenre(it) })
        AnimeSynopsis(synopsis = animeDetail.synopsis)
        CharVoiceActorList(characters = characters)
        AnimeInfo(animeDetail = animeDetail)
        DetailPictureList(pictures = animeDetail.pictures)
        RelatedAnimeList(relatedAnime = animeDetail.related_anime, navToDetail = navToDetail)
        RecommendationAnimeList(recommendations = animeDetail.recommendations, navToDetail = navToDetail)
        Spacer(modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun AnimeToolbar(
    status: AnimeDetailPresentation.MyListStatus,
    onAddPressed: () -> Unit,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
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
        },
        actions = {
            val watchStatusString = when(status.status) {
                "plan_to_watch" -> "Plan To Watch"
                "dropped" -> "Dropped"
                "on_hold" -> "On Hold"
                "watching" -> "Watching"
                "completed" -> "Completed"
                else -> ""
            }
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(end = 4.dp)
                    .height(40.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colors.background)
                    .clickable { onAddPressed() },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(status.status == "") {
                        Text(text = "Add ", style = MaterialTheme.typography.button, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground)
                        Icon(Icons.Default.Add, contentDescription = null, tint = MaterialTheme.colors.onBackground)
                    } else {
                        Text(text = "$watchStatusString ", style = MaterialTheme.typography.button, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground)
                        Box(
                            modifier = Modifier
                                .height(IntrinsicSize.Min)
                                .width(24.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.onBackground),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = status.score.toString(), style = MaterialTheme.typography.caption, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.background)
                        }
                    }
                }
            }
        }
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
                    .height(500.dp)
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
    if(synopsis == "") {
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

        Column {
            DetailSubtitle(title = "Synopsis")
            Column(
                modifier = Modifier
                    .padding(start = 40.dp, end = 16.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        enabled = isClickable,
                        indication = null
                    ) {
                        isExpanded = !isExpanded
                    }
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
}

@Composable
fun CharVoiceActorList(
    characters: List<AnimeCharactersPresentation.Data>
) {
    if(characters != emptyList<AnimeCharactersPresentation.Data>()) {
        Column {
            DetailSubtitle(title = "Voice Actors")
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
}

@Composable
fun AnimeInfo(
    animeDetail: AnimeDetailPresentation
) {
    val infoList = listOf(
        Pair("Alternative titles", "${animeDetail.alternative_titles.en}\n${animeDetail.alternative_titles.ja}${titleSynonymsToString(animeDetail.alternative_titles.synonyms)}"),
        Pair("Season", "${animeDetail.start_season.season.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }} ${animeDetail.start_season.year}"),
        Pair("Duration", "${animeDetail.num_episodes} ep, ${animeDetail.average_episode_duration / 60} min"),
        Pair("Broadcast", "${animeDetail.broadcast.day_of_the_week.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }} ${animeDetail.broadcast.start_time}"),
        Pair("Start Date", animeDetail.start_date),
        Pair("End Date", animeDetail.end_date),
        Pair("Source",  animeDetail.source.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }),
        Pair("Rating", animeDetail.rating.uppercase())
    )
    Column {
        DetailSubtitle(title = "More Info")
        Column(
            modifier = Modifier
                .padding(start = 40.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(2f),
                    text = "Studios",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.SemiBold
                )
                Column(
                    modifier = Modifier
                        .weight(3f),
                ) {
                    animeDetail.studios.forEach { studio ->
                        Text(
                            text = studio.name,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }
            infoList.forEach {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .weight(2f),
                        text = it.first,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier
                            .weight(3f),
                        text = it.second,
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }
    }
}

fun titleSynonymsToString(
    synonyms: List<String>
): String {
    var result = ""
    synonyms.forEach {
        result += "\n${it}"
    }
    return result
}

@Composable
fun DetailPictureList(
    pictures: List<AnimeDetailPresentation.Picture>
) {
    if(pictures != emptyList<AnimeDetailPresentation.Picture>()) {
        Column {
            DetailSubtitle(title = "Pictures")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 40.dp)
            ){
                items(items = pictures){ item ->
                    NetworkImage(
                        modifier = Modifier
                            .height(180.dp)
                            .shadow(4.dp, MaterialTheme.shapes.medium, true),
                        imageUrl = item.medium
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(0.dp))
                }
            }
        }
    }
}

@Composable
fun RelatedAnimeList(
    relatedAnime: List<AnimeDetailPresentation.RelatedAnime>,
    navToDetail: (Int) -> Unit
) {
    if(relatedAnime != emptyList<AnimeDetailPresentation.RelatedAnime>()) {
        Column {
            DetailSubtitle(title = "Related Anime")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 40.dp)
            ){
                items(items = relatedAnime){ item ->
                    SmallGrid(
                        id = item.node.id,
                        imageUrl = item.node.main_picture.medium,
                        title = item.node.title,
                        navToDetail = { navToDetail(it) }
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(0.dp))
                }
            }
        }
    }
}

@Composable
fun RecommendationAnimeList(
    recommendations: List<AnimeDetailPresentation.Recommendation>,
    navToDetail: (Int) -> Unit
) {
    if(recommendations != emptyList<AnimeDetailPresentation.Recommendation>()) {
        Column {
            DetailSubtitle(title = "Recommendations")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 40.dp)
            ){
                items(items = recommendations){ item ->
                    SmallGrid(
                        id = item.node.id,
                        imageUrl = item.node.main_picture.medium,
                        title = item.node.title,
                        navToDetail = { navToDetail(it) }
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(0.dp))
                }
            }
        }
    }
}

@Composable
fun DetailSubtitle(
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colors.primary)
        )
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.SemiBold
        )
    }
}

//@Preview
//@Composable
//fun AnimeScreenPreview() {
//    RisutoTheme {
//        Column(
//            Modifier
//                .background(MaterialTheme.colors.background)
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.spacedBy(24.dp)
//        ) {
//            AnimeContent(
//                animeDetail = FakeItems.animeDetail,
//                characters = listOf(FakeItems.character, FakeItems.character, FakeItems.character, FakeItems.character, FakeItems.character, FakeItems.character),
//                onBackPressed = {},
//                navToSearchWithGenre = {},
//                navToDetail = {}
//            )
//        }
//    }
//}

@Preview
@Composable
fun AnimeInfoPreview() {
    RisutoTheme {
        Column(
            Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            CharVoiceActorList(
                characters = listOf(
                    FakeItems.character,
                    FakeItems.character,
                    FakeItems.character,
                    FakeItems.character,
                    FakeItems.character,
                    FakeItems.character
                )
            )
            AnimeInfo(animeDetail = FakeItems.animeDetail)
        }
    }
}

@Preview
@Composable
fun AnimeSuggestionPreview() {
    RisutoTheme {
        Column(
            Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            DetailPictureList(pictures = FakeItems.animeDetail.pictures)
            RelatedAnimeList(relatedAnime = FakeItems.animeDetail.related_anime, navToDetail = {})
            RecommendationAnimeList(recommendations = FakeItems.animeDetail.recommendations, navToDetail = {})
        }
    }
}

//@Preview
//@Composable
//fun AnimeToolbarPreview() {
//    RisutoTheme {
//        AnimeToolbar(onAddPressed = {}, onBackPressed = {})
//    }
//}