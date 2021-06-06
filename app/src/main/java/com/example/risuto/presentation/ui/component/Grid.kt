package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.model.MyAnimePresentation
import com.example.risuto.presentation.util.generateFakeItem
import com.example.risuto.presentation.util.generateFakeItemList
import com.example.risuto.presentation.util.intToCurrency

@Composable
fun SmallGrid(
    modifier: Modifier = Modifier,
    item: AnimeListPresentation,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier
            .wrapContentSize()
            .width(100.dp)
            .clickable {
                navToDetail(item.mal_id)
            }
    ) {
        NetworkImage(
            imageUrl = item.image_url,
            modifier = Modifier
                .size(width = 120.dp, height = 160.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, true)
        )
        Text(text = item.title + "\n",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MediumGrid(
    modifier: Modifier = Modifier,
    item: AnimeListPresentation,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                navToDetail(item.mal_id)
            }
    ) {
        NetworkImage(
            imageUrl = item.image_url,
            modifier = Modifier
                .size(width = 180.dp, height = 240.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, clip = true)
        )
        Text(text = item.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.requiredHeight(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(Icons.Default.Star, contentDescription = null)
            Text(text = item.score.toString(), style = MaterialTheme.typography.caption)
            Icon(Icons.Default.Person, contentDescription = null)
            Text(text = intToCurrency(item.members), style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun MyAnimeMediumGrid(
    modifier: Modifier = Modifier,
    item: MyAnimePresentation,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                navToDetail(item.mal_id)
            }
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            NetworkImage(
                imageUrl = item.image_url,
                modifier = Modifier
                    .size(width = 180.dp, height = 240.dp)
                    .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, clip = true)
            )
            Box(modifier = Modifier.background(Color.LightGray)) {
                Text(text = item.myScore.toString(), color = Color.White)
            }
        }
        Text(text = item.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun SmallGridPreview() {
    SmallGrid(item = generateFakeItem(), navToDetail = {})
}

@Preview
@Composable
fun MediumGridPreview() {
    MediumGrid(item = generateFakeItem(), navToDetail = {})
}