package com.lexwilliam.risuto.ui.screens.search

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lexwilliam.risuto.model.SearchHistoryPresentation
import com.lexwilliam.risuto.model.AnimePresentation
import com.lexwilliam.risuto.model.ShortAnimePresentation
import com.lexwilliam.risuto.ui.component.ImeAvoidingBox
import com.lexwilliam.risuto.ui.component.LoadingScreen
import com.lexwilliam.risuto.ui.component.RowItem
import com.lexwilliam.risuto.ui.component.SmallGrid
import com.lexwilliam.risuto.ui.theme.RisutoTheme
import com.lexwilliam.risuto.util.FakeItems
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    state: SearchContract.State,
    onEventSent: (SearchContract.Event) -> Unit,
    navToDetail: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var cursorColor by remember { mutableStateOf(Color.Black) }
    Timber.d(state.resultType.name)
    Timber.d(state.genres.toString())
    if(state.q == "" && state.genres == "-1") {
        onEventSent(SearchContract.Event.OnResultChanged(ResultType.History))
    }
    if(state.isLoading) {
        LoadingScreen()
    } else {
        SearchContent(
            searchSuggestions = state.searchAnimes,
            animes = state.searchAnimesPaging,
            searchHistory = state.searchHistory,
            animeHistory = state.animeHistory,
            isRefreshing = state.isRefreshing,
            onEventSent = { onEventSent(it) },
            query = state.q,
            resultType = state.resultType,
            genres = state.genres,
            keyboardController = keyboardController,
            cursorColor = cursorColor,
            onCursorChanged = { cursorColor = it },
            navToDetail = { navToDetail(it) },
            onBackPressed = { onBackPressed() }
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun SearchContent(
    searchSuggestions: List<AnimePresentation.Data>,
    animes: Flow<PagingData<AnimePresentation.Data>>?,
    searchHistory: List<SearchHistoryPresentation>,
    animeHistory: List<ShortAnimePresentation>,
    isRefreshing: Boolean,
    onEventSent: (SearchContract.Event) -> Unit,

    keyboardController: SoftwareKeyboardController?,
    cursorColor: Color,
    onCursorChanged: (Color) -> Unit,

    query: String?,
    resultType: ResultType,
    genres: String?,

    navToDetail: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .navigationBarsWithImePadding()
    ) {
        SearchBar(
            query = query,
            genres = genres,
            resultType = resultType,
            cursorColor = cursorColor,
            onCursorChanged = { onCursorChanged(it) },
            onEventSent = { onEventSent(it) },
            onBackPressed = { onBackPressed() },
        )
        SearchView(
            searchSuggestions = searchSuggestions,
            animes = animes,
            searchHistory = searchHistory,
            animeHistory = animeHistory,
            isRefreshing = isRefreshing,
            onEventSent = { onEventSent(it) },
            keyboardController = keyboardController,
            onCursorChanged = { onCursorChanged(it) },
            query = query,
            genres = genres,
            resultType = resultType,
            navToDetail = { navToDetail(it) }
        )
        ImeAvoidingBox()
    }
}

@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    query: String?,
    genres: String?,
    resultType: ResultType,
    cursorColor: Color,
    onCursorChanged: (Color) -> Unit,
    onEventSent: (SearchContract.Event) -> Unit,
    onBackPressed: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = FocusRequester()
    val isFocused by interactionSource.collectIsPressedAsState()

    if(isFocused) {
        onCursorChanged(Color.Black)
    }
    TopAppBar(
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars,
            applyBottom = false,
        ),
        title = {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .focusRequester(focusRequester),
                value = query?:"",
                onValueChange = {
                    onEventSent(SearchContract.Event.OnQueryChanged(q = it, genres = genres))
                    onEventSent(SearchContract.Event.OnResultChanged(ResultType.Suggestion))
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
                    onEventSent(SearchContract.Event.OnQueryChanged(q = query, genres = genres))
                    onEventSent(SearchContract.Event.OnResultChanged(ResultType.FullResult))
                    onEventSent(SearchContract.Event.InsertSearchHistory(query?:""))
                    onCursorChanged(Color.Transparent)
                })
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = MaterialTheme.colors.secondary)
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.secondary
    )
    if(resultType == ResultType.History) {
        DisposableEffect(Unit) {
            focusRequester.requestFocus()
            onDispose { }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun SearchView(
    searchSuggestions: List<AnimePresentation.Data>,
    animes: Flow<PagingData<AnimePresentation.Data>>?,
    searchHistory: List<SearchHistoryPresentation>,
    animeHistory: List<ShortAnimePresentation>,
    isRefreshing: Boolean,
    onEventSent: (SearchContract.Event) -> Unit,

    keyboardController: SoftwareKeyboardController?,
    onCursorChanged: (Color) -> Unit,

    query: String?,
    resultType: ResultType,
    genres: String?,

    navToDetail: (Int) -> Unit
) {
    when(resultType){
        ResultType.FullResult -> {
            keyboardController?.hide()
            LaunchedEffect(1) {
                onEventSent(SearchContract.Event.SearchAnimePaging)
            }
            ResultView(animes = animes, isRefreshing = isRefreshing, onEventSent = { onEventSent(it) }, navToDetail = { navToDetail(it) })
        }
        ResultType.Suggestion -> {
            LaunchedEffect(query) {
                delay(1000)
                onEventSent(SearchContract.Event.SearchAnime)
            }
            SuggestionView(
                items = searchSuggestions.map { SearchHistoryPresentation(query = it.title) },
                onSelectItem = {
                    onEventSent(SearchContract.Event.OnQueryChanged(q = it.query, genres = genres))
                    onEventSent(SearchContract.Event.InsertSearchHistory(query?:""))
                    onCursorChanged(Color.Transparent)
                    onEventSent(SearchContract.Event.OnResultChanged(ResultType.FullResult))
                }
            )
        }
        ResultType.History -> {
            HistoryView(
                animeHistory = animeHistory,
                searchHistory = searchHistory,
                onEventSent = { onEventSent(it) },
                navToDetail = { navToDetail(it) },
                onCursorChanged = { onCursorChanged(it) }
            )
        }
    }
}

enum class ResultType{
    Suggestion, History, FullResult
}

@Composable
fun ResultView(
    animes: Flow<PagingData<AnimePresentation.Data>>?,
    isRefreshing: Boolean,
    onEventSent: (SearchContract.Event) -> Unit,
    navToDetail: (Int) -> Unit
) {
    if(animes != null) {
        val lazyAnimeList = animes.collectAsLazyPagingItems()
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = {
                onEventSent(SearchContract.Event.RefreshPaging)
            }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(lazyAnimeList) { anime ->
                    RowItem(modifier = Modifier.padding(start = 16.dp, end = 16.dp), item = anime!!, navToDetail = { navToDetail(anime.mal_id) })
                }
                lazyAnimeList.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { LoadingScreen() }
                        }
                        loadState.append is LoadState.Loading -> {
                            item { LoadingScreen() }
                        }
                        loadState.refresh is LoadState.Error -> {
                            val e = lazyAnimeList.loadState.refresh as LoadState.Error
                            item {
                                Text(
                                    text = e.error.localizedMessage!!,
                                    modifier = Modifier.fillParentMaxSize()
                                )
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            val e = lazyAnimeList.loadState.append as LoadState.Error
                            item {
                                Text(
                                    text = e.error.localizedMessage!!
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryView(
    animeHistory: List<ShortAnimePresentation>,
    searchHistory: List<SearchHistoryPresentation>,
    onEventSent: (SearchContract.Event) -> Unit,
    navToDetail: (Int) -> Unit,
    onCursorChanged: (Color) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if(animeHistory.isNotEmpty()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                        .wrapContentWidth(Alignment.Start),
                    text = "History",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            onEventSent(SearchContract.Event.DeleteAllAnimeHistory)
                        }
                        .weight(1f)
                        .padding(end = 16.dp)
                        .wrapContentWidth(Alignment.End),
                    text = "Delete All", color = Color.Red,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            HistoryHorizontalGridList(animeHistory = animeHistory, navToDetail = { navToDetail(it) })
        }
        if(searchHistory.isNotEmpty()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                        .wrapContentWidth(Alignment.Start),
                    text = "Recent Search",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            onEventSent(SearchContract.Event.DeleteAllSearchHistory)
                        }
                        .weight(1f)
                        .padding(end = 16.dp)
                        .wrapContentWidth(Alignment.End),
                    text = "Delete All", color = Color.Red,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            QueryListWithDelete(
                items = searchHistory.map { SearchHistoryPresentation(query = it.query) },
                onSelectItem = {
                    onEventSent(SearchContract.Event.OnQueryChanged(q = it.query))
                    onEventSent(SearchContract.Event.OnResultChanged(ResultType.FullResult))
                    onCursorChanged(Color.Transparent)
                },
                onDeleteItem = {
                    onEventSent(SearchContract.Event.DeleteSearchHistory(it))
                }
            )
        }
    }
}

@Composable
fun SuggestionView(
    items: List<SearchHistoryPresentation>,
    onSelectItem: (SearchHistoryPresentation) -> Unit
) {
    Column {
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
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
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

@Composable
fun HistoryHorizontalGridList(
    animeHistory: List<ShortAnimePresentation>,
    navToDetail: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp)
    ){
        items(items = animeHistory){ item ->
            SmallGrid(
                id = item.mal_id,
                imageUrl = item.image_url,
                title = item.title,
                navToDetail = { navToDetail(it) }
            )
        }
        item {
            Spacer(modifier = Modifier.padding(0.dp))
        }
    }
}

//@Preview
//@Composable
//fun ResultPreview() {
//    RisutoTheme {
//        Box(
//            Modifier.background(MaterialTheme.colors.background)
//        ) {
//            ResultView(
//                animes = flowOf(PagingData.from(listOf(FakeItems.animeData, FakeItems.animeData, FakeItems.animeData, FakeItems.animeData))),
//                navToDetail = {}
//            )
//        }
//        }
//    }


@Preview
@Composable
fun QueryPreview() {
    RisutoTheme {
        Box(
            Modifier.background(MaterialTheme.colors.background)
        ) {
            SuggestionView(items = listOf(SearchHistoryPresentation(query = "test123"), SearchHistoryPresentation(query = "test123"), SearchHistoryPresentation(query = "test123")), onSelectItem = {})
        }
    }
}

@Preview
@Composable
fun HistoryPreview() {
    RisutoTheme {
        Box(
            Modifier.background(MaterialTheme.colors.background)
        ) {
            HistoryView(
                animeHistory = listOf(FakeItems.shortAnime, FakeItems.shortAnime, FakeItems.shortAnime, FakeItems.shortAnime, FakeItems.shortAnime),
                searchHistory = listOf(SearchHistoryPresentation(query = "test123")),
                onEventSent = {},
                navToDetail = {},
                onCursorChanged = {}
            )
        }
    }
}