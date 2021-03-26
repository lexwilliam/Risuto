package com.example.risuto.presentation.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.risuto.presentation.model.RowStylePresentation
import com.example.risuto.presentation.util.generateFakeRowItem
import com.example.risuto.presentation.util.generateFakeRowItemList

@Composable
fun SearchScreen() {
    SearchContent()
}

@Composable
fun SearchContent() {

}

@Composable
fun SearchToolBar(){
    val textState = remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

    }
}

@Composable
fun SearchList(
    items: List<RowStylePresentation>
) {
    LazyColumn(
    ) {
        items(items = items){ item ->
            SearchRowItem(item = item, modifier = Modifier.padding(8.dp))
            Divider()
        }
    }
}

@Composable
fun SearchRowItem(
    item: RowStylePresentation,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
        )
        Text(text = item.title)
    }
}



@Preview
@Composable
fun SmallRowItemPreview() {
    SearchRowItem(item = generateFakeRowItem())
}

@Preview
@Composable
fun PreviewSearchList() {
    SearchList(items = generateFakeRowItemList())
}