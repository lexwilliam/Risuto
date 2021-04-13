package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.util.generateFakeItem
import com.example.risuto.presentation.util.intToCurrency

@Composable
fun RowItem(
    item: AnimeListPresentation,
    modifier: Modifier = Modifier,
    navToDetail: (Int) -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .clickable {
                navToDetail(item.mal_id)
            }
            .height(180.dp)) {
        NetworkImage(
            imageUrl = item.image_url,
            modifier = Modifier
                .size(width = 120.dp, height = 160.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, true)
        )
        Column(modifier = Modifier
            .padding(start = 16.dp)) {
            Text(
                text = item.title,
                modifier = Modifier.padding(bottom = 6.dp),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.type + " (" + item.episodes + ")",
                modifier = Modifier.padding(bottom = 6.dp),
                style = MaterialTheme.typography.body1
            )
            Row(
                modifier = Modifier.padding(bottom = 6.dp)
            ) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = null
                )
                Text(
                    text = item.score.toString(),
                    modifier = Modifier.padding(end = 4.dp),
                    style = MaterialTheme.typography.body1
                )
            }
            Row(
                modifier = Modifier.padding(bottom = 6.dp)
            ) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = null
                )
                Text(
                    text = intToCurrency(item.members),
                    maxLines = 1,
                    style = MaterialTheme.typography.body1
                )
            }
        } 
    }
}

@Preview
@Composable
fun RowItemPreview() {
    RowItem(item = generateFakeItem(), navToDetail = {})
}