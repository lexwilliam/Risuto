package com.example.risuto.presentation.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.model.QuerySearch
import com.example.risuto.presentation.ui.component.ChipGroupList
import com.example.risuto.presentation.ui.component.ColumnList
import com.example.risuto.presentation.ui.component.GridList
import com.example.risuto.presentation.ui.component.Header
import com.example.risuto.presentation.util.generateFakeItemList

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    navToDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    SearchContent(
        items = viewState.searchAnimes,
        onQueryChange = viewModel::onSearchAnime,
        navToDetail = { navToDetail(it) }
    )
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun SearchContent(
    items: List<AnimeListPresentation>,
    onQueryChange: (QuerySearch) -> Unit,
    navToDetail: (Int) -> Unit
) {
    var text by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    var headerState by remember { mutableStateOf(true) }

    var resultType by remember { mutableStateOf(ResultType.Filter) }
    if(text.isEmpty()){
        resultType = ResultType.Filter
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if(headerState) {
            SearchTopBar()
        }
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
                onQueryChange(QuerySearch(it, null, null, null, 5))
                if(isFocused){
                    resultType = ResultType.FullResult
                    headerState = false
                }
            },
            interactionSource = interactionSource,
            textStyle = MaterialTheme.typography.subtitle1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions( onDone = {
                onQueryChange(QuerySearch(text, null, null, null, null))
                keyboardController?.hideSoftwareKeyboard()
            }),
            decorationBox = {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.small, clip = true)
                        .size(width = 120.dp, height = 40.dp)
                ) {
                    Row(
                        modifier = Modifier.background(color = MaterialTheme.colors.onPrimary),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.padding(4.dp))
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSecondary
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        it()
                    }
                }
            }
        )
        when(resultType){
            ResultType.FullResult ->
                GridList(items = items, navToDetail = { navToDetail(it) })
            ResultType.Filter ->
                ChipGroupList(onClick = { text = it })
        }
    }
}

enum class ResultType{
    FullResult, Filter
}

@Composable
fun SearchTopBar() {
    Header(title = "Search")
}

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Preview
@Composable
fun SearchScreenPreview() {
    SearchContent(
        items = generateFakeItemList(),
        onQueryChange = {},
        navToDetail = {}
    )
}