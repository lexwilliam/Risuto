package com.lexwilliam.risuto.ui.component

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.lexwilliam.risuto.model.AnimePresentation
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
            var genresStr = ""
            item.genres.forEach { genresStr += "${it.name} " }
            Text(
                text = genresStr,
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.surface
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
                Text(text = item.score.toString(), style = MaterialTheme.typography.caption)
                Icon(modifier = Modifier.size(14.dp), imageVector = Icons.Default.Person, contentDescription = null)
                Text(text = intToCurrency(item.members), style = MaterialTheme.typography.caption)
            }
        }
    }
}



//@Preview
//@Composable
//fun RowItemPreview() {
//    RowItem(item = generateFakeItem(), navToDetail = {})
//}