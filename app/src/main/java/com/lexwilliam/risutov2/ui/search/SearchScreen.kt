package com.lexwilliam.risutov2.ui.search

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
import androidx.compose.material.icons.filled.Delete
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
import com.lexwilliam.risutov2.model.AnimeListPresentation
import com.lexwilliam.risutov2.model.QuerySearch
import com.lexwilliam.risutov2.model.SearchHistoryPresentation
import com.lexwilliam.risutov2.util.bottomNavGap
import com.lexwilliam.risutov2.ui.component.GridList
import com.lexwilliam.risutov2.ui.component.HorizontalGridList

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
        deleteSearchHistory = viewModel::deleteSearchHistory,
        deleteAllSearchHistory = viewModel::deleteAllSearchHistory,
        deleteAnimeHistory = viewModel::deleteAnimeHistory,
        deleteAllAnimeHistory = viewModel::deleteAllAnimeHistory,
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
    deleteSearchHistory: (String) -> Unit,
    deleteAllSearchHistory: () -> Unit,
    deleteAnimeHistory: (String) -> Unit,
    deleteAllAnimeHistory: () -> Unit,
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
                keyboardController?.hide()
                getQuery(QuerySearch(limit = null))
                GridList(items = items, navToDetail = { navToDetail(it) })
            }
            ResultType.Result -> {
                QueryList(
                    items = items.map { SearchHistoryPresentation(query = it.title) },
                    onSelectItem = {
                    onQueryChanged(it.query)
                    getQuery(QuerySearch(q = it.query))
                    onSearchAnime()
                    insertSearchHistory(SearchHistoryPresentation(query = it.query))
                    cursorColor = Color.Transparent
                    onResultChange(ResultType.FullResult)
                    }
                )
            }
            ResultType.History -> {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = _root_ide_package_.com.lexwilliam.risutov2.util.bottomNavGap),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if(animeHistory.isNotEmpty()) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .wrapContentWidth(Alignment.Start),
                                text = "History",
                                style = MaterialTheme.typography.subtitle2
                            )
                            Text(
                                modifier = Modifier
                                    .clickable {
                                        deleteAllAnimeHistory()
                                    }
                                    .weight(1f)
                                    .wrapContentWidth(Alignment.End),
                                text = "Delete All", color = Color.Red,
                                style = MaterialTheme.typography.subtitle2
                            )
                        }
                        HorizontalGridList(items = animeHistory, navToDetail = { navToDetail(it) })
                    }
                    if(searchHistory.isNotEmpty()) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .wrapContentWidth(Alignment.Start),
                                text = "Recent Search",
                                style = MaterialTheme.typography.subtitle2
                            )
                            Text(
                                modifier = Modifier
                                    .clickable {
                                        deleteAllSearchHistory()
                                    }
                                    .weight(1f)
                                    .wrapContentWidth(Alignment.End),
                                text = "Delete All", color = Color.Red,
                                style = MaterialTheme.typography.subtitle2
                            )
                        }
                        QueryListWithDelete(
                            items = searchHistory.map { SearchHistoryPresentation(query = it.query) },
                            onSelectItem = {
                                onQueryChanged(it.query)
                                getQuery(QuerySearch(q = it.query))
                                onSearchAnime()
                                onResultChange(ResultType.FullResult)
                                cursorColor = Color.Transparent
                            },
                            onDeleteItem = {
                                deleteSearchHistory(it)
                            }
                        )
                    }

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
    items: List<SearchHistoryPresentation>,
    onSelectItem: (SearchHistoryPresentation) -> Unit
) {
    Column(
    ) {
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .height(40.dp)
                    .clickable { onSelectItem(item) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colors.surface)
                Text(text = item.query, style = MaterialTheme.typography.subtitle2)
            }
        }
    }
}

@Composable
fun QueryListWithDelete(
    items: List<SearchHistoryPresentation>,
    onSelectItem: (SearchHistoryPresentation) -> Unit,
    onDeleteItem: (String) -> Unit
) {
    Column(
    ) {
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .height(40.dp)
                    .clickable { onSelectItem(item) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(modifier = Modifier
                    .wrapContentWidth(Alignment.Start)
                    .weight(1f), imageVector = Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colors.surface)
                Text(modifier = Modifier
                    .wrapContentWidth(Alignment.Start)
                    .weight(4f), text = item.query, style = MaterialTheme.typography.subtitle2)
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End),
                    onClick = { onDeleteItem(item.query) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colors.surface
                    )
                }
            }
        }
    }
}