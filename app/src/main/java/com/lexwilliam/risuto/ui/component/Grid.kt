package com.lexwilliam.risuto.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
fun SmallGrid(
    modifier: Modifier = Modifier,
    id: Int,
    imageUrl: String,
    title: String,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier
            .wrapContentSize()
            .width(100.dp)
            .clickable {
                navToDetail(id)
            }
    ) {
        NetworkImage(
            imageUrl = imageUrl,
            modifier = Modifier
                .size(width = 120.dp, height = 160.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.small, true)
        )
        Text(text = title + "\n",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
fun SmallGridPerson(
    modifier: Modifier = Modifier,
    id: Int,
    imageUrl: String,
    title: String,
    navToPerson: (Int) -> Unit
) {
    Column(
        modifier
            .wrapContentSize()
            .width(100.dp)
            .clickable {
                navToPerson(id)
            }
    ) {
        NetworkImage(
            imageUrl = imageUrl,
            modifier = Modifier
                .size(width = 120.dp, height = 160.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.small, true)
        )
        Text(text = title + "\n",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
fun MediumGrid(
    modifier: Modifier = Modifier,
    id: Int,
    imageUrl: String,
    title: String,
    score: Double,
    members: Int,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                navToDetail(id)
            }
    ) {
        NetworkImage(
            imageUrl = imageUrl,
            modifier = Modifier
                .size(width = 180.dp, height = 240.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, clip = true)
        )
        Text(
            text = title,
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
            Icon(modifier = Modifier.size(14.dp), imageVector = Icons.Default.Star, contentDescription = null)
            Text(text = if(score != -1.0) score.toString() else "N/A", style = MaterialTheme.typography.caption)
            Icon(modifier = Modifier.size(14.dp), imageVector = Icons.Default.Person, contentDescription = null)
            Text(text = intToCurrency(members), style = MaterialTheme.typography.caption)
        }
    }
}

//@Preview
//@Composable
//fun SmallGridPreview() {
//    SmallGrid(item = generateFakeItem(), navToDetail = {})
//}
//
//@Preview
//@Composable
//fun MediumGridPreview() {
//    MediumGrid(item = generateFakeItem(), navToDetail = {})
//}