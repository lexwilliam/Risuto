package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.util.generateFakeItem
import com.example.risuto.presentation.util.generateFakeItemList

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
                .size(width = 100.dp, height = 160.dp)
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
                .size(width = 180.dp, height = 200.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, true)
        )
        Text(text = item.title + "\n",
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