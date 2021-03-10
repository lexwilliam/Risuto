package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.example.risuto.presentation.model.custom.RowStylePresentation
import com.example.risuto.presentation.util.generateFakeRowItem
import com.example.risuto.presentation.util.generateFakeRowItemList
import com.example.risuto.presentation.util.intToCurrency
import dev.chrisbanes.accompanist.glide.GlideImage


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
    Row(modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp), true)
            .background(color = Color.White)) {
        Box(modifier = Modifier
            .size(width = 136.dp, height = 180.dp)
            .background(Color.Gray)
        ) {
            if (item.image_url.isNotEmpty()) {
                GlideImage(
                    data = item.image_url.replace("\\\\", "/"),
                    contentDescription = "Anime Picture",
                    fadeIn = true,
                    modifier = Modifier.fillMaxSize(),
                    requestBuilder = {
                        val options = RequestOptions()
                        options
                            .centerCrop()
                        apply(options)
                    }
                )
            }
        }
        Column(modifier = Modifier
            .padding(start = 8.dp)
            .background(Color.White)) {
            Text(text = item.title, modifier = Modifier.padding(bottom = 4.dp), fontWeight = FontWeight.Bold)
            Text(text = item.type + " (" + item.episodes + ")", modifier = Modifier.padding(bottom = 4.dp))
            Row(modifier = Modifier.padding(bottom = 4.dp)) {
                Icon(Icons.Filled.Star, contentDescription = null)
                Text(text = item.score.toString(), modifier = Modifier.padding(end = 4.dp))
                Icon(Icons.Filled.Person, contentDescription = null)
                Text(text = intToCurrency(item.members), maxLines = 1)
            }
            Text(text = "Synopsis:", fontWeight = FontWeight.Bold)
            Text(text = item.synopsis, maxLines = 3, overflow = TextOverflow.Ellipsis)
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