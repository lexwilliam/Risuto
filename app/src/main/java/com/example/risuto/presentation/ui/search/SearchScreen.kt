package com.example.risuto.presentation.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.model.QuerySearch
import com.example.risuto.presentation.ui.component.*
import com.example.risuto.presentation.util.generateFakeItemList

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    navToDetail: (Int) -> Unit,
    navToGenre: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    SearchContent(
        items = viewState.searchAnimes,
        query = viewState.query,
        onSearchAnime = viewModel::onSearchAnime,
        getQuery = viewModel::getQuery,
        getGenre = viewModel::getGenre,
        getOrder = viewModel::getOrderBy,
        navToDetail = { navToDetail(it) },
        navToGenre = { navToGenre(it) }
    )
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun SearchContent(
    items: List<AnimeListPresentation>,
    query: QuerySearch,
    onSearchAnime: () -> Unit,
    getQuery: (String) -> Unit,
    getGenre: (Int) -> Unit,
    getOrder: (String) -> Unit,
    navToDetail: (Int) -> Unit,
    navToGenre: (Int) -> Unit
) {
    var headerState by remember { mutableStateOf(true) }
    var text by remember { mutableStateOf("") }
    var showFilterBtn by remember { mutableStateOf(false) }
    var resultState by remember { mutableStateOf(ResultType.Filter) }

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if(headerState) {
            SearchTopBar()
        }
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
                getQuery(it)
            },
            interactionSource = interactionSource,
            textStyle = MaterialTheme.typography.subtitle1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions( onDone = {
                onSearchAnime()
                keyboardController?.hideSoftwareKeyboard()
            }),
            decorationBox = {
                if(isFocused){
                    resultState = ResultType.Result
                    headerState = false
                }
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 8.dp,
                            shape = MaterialTheme.shapes.small,
                            clip = true
                        )
                        .size(width = 120.dp, height = 40.dp),
                    color = Color.White
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.padding(4.dp))
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        it()
                    }
                }
            }
        )
        when(resultState){
            ResultType.Result -> {
                showFilterBtn = true
                GridList(items = items, navToDetail = { navToDetail(it) })
            }
            ResultType.Filter -> {
                FilterGenre(navToGenre = {
                    navToGenre(it)
                })
            }
        }
    }
}

private enum class ResultType{
    Result, Filter
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
        query = QuerySearch(),
        onSearchAnime = {},
        getGenre = {},
        getQuery = {},
        getOrder = {},
        navToDetail = {},
        navToGenre = {}
    )
}