package com.lexwilliam.risuto.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.util.FakeItems
import com.lexwilliam.risuto.util.intToCurrency

@Composable
fun RowItem(
    item: AnimePresentation.Data,
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
            imageUrl = item.images.jpg.image_url,
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, true)
        )
        Column(modifier = Modifier
            .padding(start = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.type + " (" + item.episodes + ")",
                style = MaterialTheme.typography.caption
            )
            Row(
                modifier = Modifier.requiredHeight(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(modifier = Modifier.size(14.dp), imageVector = Icons.Default.Star, contentDescription = null)
                Text(text = if(item.score == -1.0) "N/A" else item.score.toString(), style = MaterialTheme.typography.caption)
                Icon(modifier = Modifier.size(14.dp), imageVector = Icons.Default.Person, contentDescription = null)
                Text(text = intToCurrency(item.members), style = MaterialTheme.typography.caption)
            }
            FlowRow(
                mainAxisSpacing = 8.dp,
                crossAxisSpacing = 8.dp
            ) {
                item.genres.forEach { genre ->
                    Box(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(color = MaterialTheme.colors.primary)
                            .wrapContentSize(),
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
        }
    }
}



@Preview
@Composable
fun RowItemPreview() {
    RowItem(item = FakeItems.animeData, navToDetail = {})
}