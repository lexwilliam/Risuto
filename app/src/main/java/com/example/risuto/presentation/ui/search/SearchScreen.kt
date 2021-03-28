package com.example.risuto.presentation.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.RowStylePresentation
import com.example.risuto.presentation.util.generateFakeRowItemList

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel()
) {
    val viewState by viewModel.state.collectAsState()
    SearchContent(
        query = viewState.query,
        searchAnimes = viewState.searchAnimes
    )
}

@Composable
fun SearchContent(
    query: String,
    searchAnimes: List<RowStylePresentation>
) {
    SearchToolBar()
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
        Text("This is Search Screen")
    }
}

@Composable
fun SearchList(
    items: List<RowStylePresentation>
) {
    Column {
        for(i in 1..8){
            SearchRowItem(item = items[i])
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
fun PreviewSearchToolBar() {
    SearchToolBar()
}

@Preview
@Composable
fun PreviewSearchList() {
    SearchList(items = generateFakeRowItemList())
}