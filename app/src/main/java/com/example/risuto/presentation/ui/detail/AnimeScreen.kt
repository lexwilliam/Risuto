package com.example.risuto.presentation.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimePresentation
import com.example.risuto.presentation.ui.component.NetworkImage
import com.example.risuto.presentation.util.generateFakeAnimeDetail
import com.example.risuto.presentation.util.genresToString
import com.example.risuto.presentation.util.intToCurrency
import com.example.risuto.presentation.util.spaceToNextLine

@Composable
fun AnimeScreen(
    viewModel: AnimeViewModel = viewModel(),
    onBackPressed: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    AnimeContent(
        animeDetail = viewState.animeDetail,
        onBackPressed = { onBackPressed() }
    )
}

@Composable
fun AnimeContent(
    animeDetail: AnimePresentation,
    onBackPressed: () -> Unit
) {
    AnimeTrailer(trailer_url = "", onBackPressed = { onBackPressed() })
    Column(modifier = Modifier
        .fillMaxSize()
        .offset(y = 160.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AnimeDetail(animeDetail = animeDetail)
        AnimeRating(animeDetail = animeDetail)
    }

}

@Composable
fun AnimeTrailer(
    trailer_url: String,
    onBackPressed: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(Color.LightGray)) {
        IconButton(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .padding(16.dp)
                .background(Color.Gray),
            onClick = { onBackPressed() }
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null)
        }
    }
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
        NetworkImage(modifier = Modifier,
            imageUrl = animeDetail.image_url, width = 120.dp, height = 180.dp)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)

        ) {
            Text(text = animeDetail.title, style = MaterialTheme.typography.h6)
            Text(
                text = animeDetail.premiered+" | "+animeDetail.type+"("+animeDetail.episodes+")",
                style = MaterialTheme.typography.caption)
//            Text(text = animeDetail.score.toString()+" | "+ intToCurrency(animeDetail.members), style = MaterialTheme.typography.caption)
            Text(text = genresToString(animeDetail.genres), style = MaterialTheme.typography.button)
            Divider()
            Text(text = animeDetail.synopsis, style = MaterialTheme.typography.body2, maxLines = 5, overflow = TextOverflow.Ellipsis)
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
        val ratings = listOf(
            Rating("Score", animeDetail.score.toString()),
            Rating("Members", intToCurrency(animeDetail.members)),
            Rating("Rank", "#"+ intToCurrency(animeDetail.rank)),
            Rating("Popularity", "#"+ intToCurrency(animeDetail.popularity)),
            Rating("Favorite", intToCurrency(animeDetail.favorites)),
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

data class Rating(
    val string: String,
    val int: String
)

@Composable
fun AnimeCast(
    animeDetail: AnimePresentation
) {

}

@Preview
@Composable
fun AnimeScreenPreview() {
    AnimeContent(
        animeDetail = generateFakeAnimeDetail(),
        onBackPressed = {}
    )
}
