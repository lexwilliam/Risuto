package com.example.risuto.presentation.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
        searchAnimes = viewState.searchAnimes,
        onValueChange = viewModel::onSearchAnime
    )
}

@Composable
fun SearchContent(
    searchAnimes: List<RowStylePresentation>,
    onValueChange: (String) -> Unit,
) {
    Column {
        var query by rememberSaveable{ mutableStateOf("") }
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            keyboardActions = KeyboardActions( onDone = {})
        )

        if(searchAnimes.isNotEmpty()) {
            Column {
                var i = 0
                for (item in searchAnimes) {
                    SearchRowItem(item = item)
                    i++
                    if (i == 8) {
                        break
                    }
                }
            }
        } else {
            Text(text = "Not Found")
        }
    }
}

@Composable
fun SearchBar(
    onValueChange: (String) -> Unit
){
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 32.dp, vertical = 8.dp)
//            .clip(MaterialTheme.shapes.large)
//            .size(width = 120.dp, height = 32.dp)
//    ) {
//        Row(
//            modifier = Modifier.background(color = MaterialTheme.colors.primary),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            var query by remember{ mutableStateOf("type") }
//            Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colors.primaryVariant)
//            OutlinedTextField(
//                value = query,
//                onValueChange = { query = it },
//                singleLine = true
//            )
//        }
//
//    }
}

@Composable
fun SearchList(
    items: List<RowStylePresentation>
) {
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

//@Preview
//@Composable
//fun PreviewSearchToolBar() {
//    SearchBar()
//}

@Preview
@Composable
fun PreviewSearchList() {
    SearchList(items = generateFakeRowItemList())
}