package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.risuto.presentation.model.RowStylePresentation
import com.example.risuto.presentation.util.generateFakeRowItem
import com.example.risuto.presentation.util.generateFakeRowItemList
import com.example.risuto.presentation.util.intToCurrency


@Composable
fun RowItemList(
    items: List<RowStylePresentation>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        items(items = items){ item ->
            RowItem(item = item)
        }
    }
}

@Composable
fun RowItem(
    item: RowStylePresentation,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(180.dp)) {
        NetworkImage(imageUrl = item.image_url, width = 120.dp, height = 160.dp)
        Column(modifier = Modifier
            .padding(start = 16.dp)) {
            Text(
                text = item.title,
                modifier = Modifier.padding(bottom = 6.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = item.type + " (" + item.episodes + ")",
                modifier = Modifier.padding(bottom = 6.dp)
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
                    modifier = Modifier.padding(end = 4.dp)
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
                    maxLines = 1
                )
            }
        } 
    }
}

@Preview
@Composable
fun RowItemPreview(){
    RowItem(item = generateFakeRowItem())
}

@Preview
@Composable
fun RowItemListPreview(){
    RowItemList(items = generateFakeRowItemList())
}