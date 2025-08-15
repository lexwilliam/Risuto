package com.lexwilliam.risuto.ui.screens.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
import com.lexwilliam.risuto.ui.component.GuestScreen
import com.lexwilliam.risuto.ui.component.MyAnimeListShimmerLoading
import com.lexwilliam.risuto.ui.component.NetworkImage
import com.lexwilliam.risuto.util.toMalFormat
import com.lexwilliam.risuto.util.watchStatusList
import com.lexwilliam.risuto.util.watchStatusToString
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun MyAnimeScreen(
    state: (MyAnimeContract.State),
    onEventSent: (MyAnimeContract.Event) -> Unit,
    navToDetail: (Int) -> Unit,
    navToProfile: () -> Unit,
    navToLogin: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var currentStatus by remember { mutableStateOf("All")}
    var currentSortType by remember { mutableStateOf("By Last Updated") }
    var currentOrderType by remember { mutableStateOf("Descending") }
    LaunchedEffect(Unit) {
        onEventSent(MyAnimeContract.Event.RefreshListWithoutView)
    }
    LaunchedEffect(state.animes) {
        onEventSent(MyAnimeContract.Event.RefreshListWithoutView)
    }
    if(state.isLoading) {
        MyAnimeListShimmerLoading()
    } else if (state.isGuest)  {
        Column(modifier = Modifier
            .navigationBarsWithImePadding()
            .padding(bottom = 56.dp)) {
            MyAnimeToolbar(
                username = "Guest",
                userImage = "",
                onSortTypeChanged = {  },
                expanded = expanded,
                isExpanded = {  },
                navToProfile = navToProfile
            )
            GuestScreen(
                navToLogin = navToLogin,
            )
        }
    } else {
        MyAnimeContent(
            myAnimeList = sortAnime(state.animes, currentSortType, currentOrderType),
            username = state.username,
            userImage = state.userImage,
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
            navToDetail = { navToDetail(it) },
            navToProfile = navToProfile,
            isLoading = state.isLoading
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun MyAnimeContent(
    myAnimeList: List<UserAnimeListPresentation.Data>,
    username: String,
    userImage: String,
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
    navToDetail: (Int) -> Unit,
    navToProfile: () -> Unit,
    isLoading: Boolean
) {
    Column(modifier = Modifier
        .navigationBarsWithImePadding()
        .padding(bottom = 56.dp)) {
        MyAnimeToolbar(
            username = username,
            userImage = userImage,
            onSortTypeChanged = { onSortTypeChanged(it) },
            expanded = expanded,
            isExpanded = { isExpanded(it) },
            navToProfile = navToProfile
        )
        MyAnimeList(
            items = myAnimeList,
            isRefreshing = isRefreshing,
            onEventSent = { onEventSent(it) },
            navToDetail = { navToDetail(it) },
            isLoading = isLoading
        )
    }
}

@Composable
fun MyAnimeToolbar(
    username: String,
    userImage: String,
    expanded: Boolean,
    isExpanded: (Boolean) -> Unit,
    onSortTypeChanged: (String) -> Unit,
    navToProfile: () -> Unit
) {
    TopAppBar(
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars,
            applyBottom = false,
        ),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NetworkImage(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .clickable { navToProfile() },
                    imageUrl = userImage
                )
                Text("$username's Anime List", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
            }
        },
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
fun MyAnimeRowItem(
    modifier: Modifier = Modifier,
    item: UserAnimeListPresentation.Data,
    onEventSent: (MyAnimeContract.Event) -> Unit,
    navToDetail: (Int) -> Unit
) {
    var myEpisode by remember { mutableStateOf(item.listStatus.numWatchedEpisodes) }
    Row(
        modifier
            .fillMaxWidth()
            .clickable {
                navToDetail(item.node.id)
            }
            .height(180.dp)) {
        NetworkImage(
            imageUrl = item.node.mainPicture.medium,
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, true)
        )
        Column(modifier = Modifier
            .padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                Modifier.weight(2f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = item.node.title,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (item.listStatus.score == -1) "${watchStatusToString(item.listStatus.status)} 0" else "${watchStatusToString(item.listStatus.status)} ${item.listStatus.score}",
                    style = MaterialTheme.typography.body1
                )
            }
            Column(
                Modifier.weight(1f),
                verticalArrangement = Arrangement.Bottom
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    progress = if(item.node.numTotalEpisodes > 0) myEpisode.toFloat() / item.node.numTotalEpisodes.toFloat() else 0f,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable {
                            if (myEpisode != item.node.numTotalEpisodes) {
                                myEpisode++
                                onEventSent(
                                    MyAnimeContract.Event.UpdateUserAnimeStatus(
                                        item.node.id,
                                        myEpisode,
                                        toMalFormat(watchStatusToString(item.listStatus.status)),
                                        item.listStatus.score
                                    )
                                )
                            }
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Text(
                        text = if(item.node.numTotalEpisodes > 0) "${myEpisode}/${item.node.numTotalEpisodes}" else "${myEpisode}/?",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@ExperimentalFoundationApi
@Composable
fun MyAnimeList(
    modifier: Modifier = Modifier,
    items: List<UserAnimeListPresentation.Data>,
    isRefreshing: Boolean,
    onEventSent: (MyAnimeContract.Event) -> Unit,
    navToDetail: (Int) -> Unit,
    isLoading: Boolean
) {
    val watchStatusTabRow = listOf("All", "Watching", "On Hold", "Plan To Watch", "Completed", "Dropped")
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    if(isLoading) {

        MyAnimeListShimmerLoading()
    } else {
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
                    LazyColumn(
                        modifier = modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        items(
                            items = when(page) {
                                0 -> items
                                1 -> items.filter { it.listStatus.status == WatchStatusPresentation.Watching }
                                2 -> items.filter { it.listStatus.status == WatchStatusPresentation.OnHold }
                                3 -> items.filter { it.listStatus.status == WatchStatusPresentation.PlanToWatch }
                                4 -> items.filter { it.listStatus.status == WatchStatusPresentation.Completed }
                                5 -> items.filter { it.listStatus.status == WatchStatusPresentation.Dropped }
                                else -> items
                            }
                        ) { item ->
                            MyAnimeRowItem(item = item, modifier = Modifier.padding(top = 16.dp, end = 16.dp), onEventSent = { onEventSent(it) }, navToDetail = { navToDetail(it) })
                        }
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
            else -> animes.sortedBy { watchStatusList.indexOf(it.listStatus.status) }
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
