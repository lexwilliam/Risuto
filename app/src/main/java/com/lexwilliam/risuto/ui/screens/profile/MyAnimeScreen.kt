package com.lexwilliam.risuto.ui.screens.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.TabRowDefaults
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lexwilliam.risuto.R
import com.lexwilliam.risuto.model.UserAnimeListPresentation
import com.lexwilliam.risuto.model.WatchStatusPresentation
import com.lexwilliam.risuto.ui.component.LoadingScreen
import com.lexwilliam.risuto.ui.component.NetworkImage
import com.lexwilliam.risuto.util.intToCurrency
import com.lexwilliam.risuto.util.watchStatusList
import com.lexwilliam.risuto.util.watchStatusToString
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun MyAnimeScreen(
    state: (MyAnimeContract.State),
    onEventSent: (MyAnimeContract.Event) -> Unit,
    navToDetail: (Int) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var currentStatus by remember { mutableStateOf("All")}
    var currentSortType by remember { mutableStateOf("By Last Updated") }
    var currentOrderType by remember { mutableStateOf("Descending") }
    if(state.isLoading) {
        LoadingScreen()
    } else {
        MyAnimeContent(
            myAnimeList = sortAnime(state.animes, currentSortType, currentOrderType),
            username = state.username,
            expanded = expanded,
            isExpanded = { expanded = it } ,
            currentStatus = currentStatus,
            onStatusChanged = { currentStatus = it },
            currentSortType = currentSortType,
            onSortTypeChanged = { currentSortType = it },
            currentOrderType = currentOrderType,
            onOrderTypeChanged = { currentOrderType = it },
            isRefreshing = state.isRefreshing,
            onEventSent = { onEventSent(it) },
            navToDetail = { navToDetail(it) }
        )
    }

}

@ExperimentalFoundationApi
@Composable
fun MyAnimeContent(
    myAnimeList: List<UserAnimeListPresentation.Data>,
    username: String,
    expanded: Boolean,
    isExpanded: (Boolean) -> Unit,
    currentStatus: String,
    onStatusChanged: (String) -> Unit,
    currentSortType: String,
    onSortTypeChanged: (String) -> Unit,
    currentOrderType: String,
    onOrderTypeChanged: (String) -> Unit,
    isRefreshing: Boolean,
    onEventSent: (MyAnimeContract.Event) -> Unit,
    navToDetail: (Int) -> Unit
) {
    Column(modifier = Modifier
        .navigationBarsWithImePadding()
        .padding(bottom = 56.dp)) {
        MyAnimeToolbar(
            username = username,
            onSortTypeChanged = { onSortTypeChanged(it) },
            currentOrderType = currentOrderType,
            onOrderTypeChanged = { onOrderTypeChanged(it) },
            expanded = expanded,
            isExpanded = { isExpanded(it) }
        )
        MyAnimeGridList(items = myAnimeList, isRefreshing = isRefreshing, onEventSent = { onEventSent(it) }, navToDetail = { navToDetail(it) })
    }
}

@Composable
fun MyAnimeToolbar(
    username: String,
    expanded: Boolean,
    isExpanded: (Boolean) -> Unit,
    onSortTypeChanged: (String) -> Unit,
    currentOrderType: String,
    onOrderTypeChanged: (String) -> Unit
) {
    TopAppBar(
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars,
            applyBottom = false,
        ),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        title = { Text("$username Anime List", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)},
        actions = {
            IconButton(onClick = { isExpanded(true) }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_filter_list_24), contentDescription = null)
            }
            val sortTypes = listOf("By Status", "By Alphabetical", "By Score", "By Last Updated")
            DropdownMenu(
                modifier = Modifier
                    .background(MaterialTheme.colors.background),
                expanded = expanded,
                onDismissRequest = { isExpanded(false) }
            ) {
                sortTypes.forEach { type ->
                    DropdownMenuItem(
                        onClick = {
                            onSortTypeChanged(type)
                            isExpanded(false)
                        }
                    ) {
                        Text(text = type)
                    }
                }
            }
        }
    )
}

@Composable
fun MyAnimeGrid(
    modifier: Modifier = Modifier,
    item: UserAnimeListPresentation.Data,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                navToDetail(item.node.id)
            }
    ) {
        NetworkImage(
            imageUrl = item.node.mainPicture.medium,
            modifier = Modifier
                .size(width = 180.dp, height = 240.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, clip = true)
        )
        Text(text = item.node.title,
            maxLines = 2,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.subtitle1
        )
        Row(
            modifier = Modifier.requiredHeight(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = watchStatusToString(item.listStatus.status), style = MaterialTheme.typography.caption)
            Text(text = intToCurrency(item.listStatus.score), style = MaterialTheme.typography.caption)
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@ExperimentalFoundationApi
@Composable
fun MyAnimeGridList(
    modifier: Modifier = Modifier,
    items: List<UserAnimeListPresentation.Data>,
    isRefreshing: Boolean,
    onEventSent: (MyAnimeContract.Event) -> Unit,
    navToDetail: (Int) -> Unit
) {
    val watchStatusTabRow = listOf("All", "Watching", "Completed", "Plan To Watch", "On Hold", "Dropped")
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    ScrollableTabRow(
        backgroundColor = MaterialTheme.colors.background,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        watchStatusTabRow.forEachIndexed { index, status ->
            Tab(
                text = { Text(text = status, style = MaterialTheme.typography.subtitle1) },
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth(),
        count = watchStatusTabRow.size,
        state = pagerState
    ) { page ->
        if(items.isEmpty()) {
            NoAnimeScreen()
        } else {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = {
                    onEventSent(MyAnimeContract.Event.RefreshList)
                },
            ) {
                LazyVerticalGrid(
                    modifier = modifier
                        .padding(start = 16.dp)
                        .fillMaxSize(),
                    cells = GridCells.Adaptive(minSize = 136.dp),
                ) {
                    items(
                        items = when(page) {
                            0 -> items
                            1 -> items.filter { it.listStatus.status == WatchStatusPresentation.Watching }
                            2 -> items.filter { it.listStatus.status == WatchStatusPresentation.Completed }
                            3 -> items.filter { it.listStatus.status == WatchStatusPresentation.PlanToWatch }
                            4 -> items.filter { it.listStatus.status == WatchStatusPresentation.OnHold }
                            5 -> items.filter { it.listStatus.status == WatchStatusPresentation.Dropped }
                            else -> items
                        }
                    ) { item ->
                        MyAnimeGrid(item = item, modifier = Modifier.padding(top = 16.dp, end = 16.dp), navToDetail = { navToDetail(it) })
                    }
                }
            }
        }
    }
}

@Composable
fun NoAnimeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(modifier = Modifier.size(80.dp), imageVector = Icons.Default.Warning, contentDescription = null, tint = Color.LightGray)
            Text(text = "No Anime Found", style = MaterialTheme.typography.h6, color = Color.LightGray)
        }
    }
}

fun sortAnime(
    animes: List<UserAnimeListPresentation.Data>,
    sortType: String,
    orderType: String
): List<UserAnimeListPresentation.Data> {
    if (orderType == "Descending") {
        return when (sortType) {
            "By Status" -> animes.sortedBy { watchStatusList.indexOf(it.listStatus.status) }
            "By Alphabetical" -> animes.sortedBy { it.node.title }
            "By Score" -> animes.sortedByDescending { it.listStatus.score }
            "By Last Update" -> animes.sortedByDescending { it.listStatus.updatedAt }
            else -> animes
        }
    } else {
        return when (sortType) {
            "By Status" -> animes.sortedBy { watchStatusList.indexOf(it.listStatus.status) }
            "By Alphabetical" -> animes.sortedBy { it.node.title }
            "By Score" -> animes.sortedBy { it.listStatus.score }
            "By Last Update" -> animes.sortedBy { it.listStatus.updatedAt }
            else -> animes
        }
    }
}
