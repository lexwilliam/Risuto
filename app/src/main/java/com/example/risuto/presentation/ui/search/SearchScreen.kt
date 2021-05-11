package com.example.risuto.presentation.ui.search

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.model.QuerySearch
import com.example.risuto.presentation.model.SearchHistoryPresentation
import com.example.risuto.presentation.ui.component.*

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    navToDetail: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    var query by rememberSaveable { mutableStateOf("") }
    var resultState by rememberSaveable { mutableStateOf(ResultType.History) }
    if(query.isEmpty()) {
        resultState = ResultType.History
    }
    SearchContent(
        items = viewState.searchAnimes,
        searchHistory = viewState.searchHistory,
        animeHistory = viewState.animeHistory,
        onSearchAnime = viewModel::onSearchAnime,
        insertSearchHistory = viewModel::insertSearchHistory,
        getQuery = viewModel::getQuery,
        query = query,
        onQueryChanged = { query = it },
        resultState = resultState,
        onResultChange = { resultState = it },
        navToDetail = { navToDetail(it) }
    ) { onBackPressed() }
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun SearchContent(
    items: List<AnimeListPresentation>,
    searchHistory: List<SearchHistoryPresentation>,
    animeHistory: List<AnimeListPresentation>,
    onSearchAnime: () -> Unit,
    insertSearchHistory: (SearchHistoryPresentation) -> Unit,
    getQuery: (QuerySearch) -> Unit,

    query: String,
    onQueryChanged: (String) -> Unit,
    resultState: ResultType,
    onResultChange: (ResultType) -> Unit,

    navToDetail: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    var cursorColor by remember { mutableStateOf(Color.Black) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .padding(bottom = 64.dp)
    ) {
        SearchBar(
            query = query,
            onQueryChanged = { onQueryChanged(it) },
            resultState = resultState,
            cursorColor = cursorColor,
            onCursorChanged = { cursorColor = it },
            onSearchAnime = onSearchAnime,
            insertSearchHistory = insertSearchHistory,
            getQuery = {
                getQuery(it)
                onResultChange(ResultType.Result)
            },
            onBackPressed = { onBackPressed() },
            onDone = {
                onResultChange(ResultType.FullResult)
            }
        )
        when(resultState){
            ResultType.FullResult -> {
                keyboardController?.hideSoftwareKeyboard()
                getQuery(QuerySearch(limit = null))
                GridList(items = items, navToDetail = { navToDetail(it) })
            }
            ResultType.Result -> {
                QueryList(items = items.map { it.title }, onSelectItem = {
                    onQueryChanged(it)
                    getQuery(QuerySearch(q = it))
                    onSearchAnime()
                    insertSearchHistory(SearchHistoryPresentation(query = it))
                    cursorColor = Color.Transparent
                    onResultChange(ResultType.FullResult)
                })
            }
            ResultType.History -> {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 64.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "Recent Search")
                    QueryList(items = searchHistory.map { it.query }, onSelectItem = {
                        onQueryChanged(it)
                        getQuery(QuerySearch(q = it))
                        onSearchAnime()
                        onResultChange(ResultType.FullResult)
                        cursorColor = Color.Transparent
                    })
                    Text(text = "History")
                    HorizontalGridList(items = animeHistory, navToDetail = { navToDetail(it) })
                }
            }
        }
    }
}

enum class ResultType{
    Result, History, FullResult
}

@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    resultState: ResultType,
    cursorColor: Color,
    onCursorChanged: (Color) -> Unit,
    onSearchAnime: () -> Unit,
    insertSearchHistory: (SearchHistoryPresentation) -> Unit,
    getQuery: (QuerySearch) -> Unit,
    onBackPressed: () -> Unit,
    onDone: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = FocusRequester()
    val isFocused by interactionSource.collectIsPressedAsState()

    if(isFocused) {
        onCursorChanged(Color.Black)
    }
    TopAppBar(
        title = {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .focusRequester(focusRequester),
                value = query,
                onValueChange = {
                    onQueryChanged(it)
                    getQuery(QuerySearch(q = it, limit = 6))
                    onSearchAnime()
                },
                interactionSource = interactionSource,
                textStyle = MaterialTheme.typography.subtitle1,
                cursorBrush = SolidColor(cursorColor),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions( onDone = {
                    getQuery(QuerySearch(q = query))
                    onSearchAnime()
                    onDone()
                    onCursorChanged(Color.Transparent)
                    insertSearchHistory(SearchHistoryPresentation(query = query))
                })
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = MaterialTheme.colors.secondary)
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.secondary
    )
    if(resultState == ResultType.History) {
        DisposableEffect(Unit) {
            focusRequester.requestFocus()
            onDispose { }
        }
    }
}

@Composable
fun QueryList(
    items: List<String>,
    onSelectItem: (String) -> Unit
) {
    Column(
    ) {
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(40.dp)
                    .clickable {
                        onSelectItem(item)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.padding(4.dp))
                Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colors.surface)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = item, style = MaterialTheme.typography.subtitle2)
            }
        }
    }
}