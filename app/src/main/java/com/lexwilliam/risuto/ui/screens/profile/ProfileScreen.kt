package com.lexwilliam.risuto.ui.screens.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lexwilliam.risuto.model.UserAnimeListPresentation
import com.lexwilliam.risuto.model.WatchStatusPresentation
import com.lexwilliam.risuto.ui.component.LoadingScreen
import com.lexwilliam.risuto.ui.component.NetworkImage
import com.lexwilliam.risuto.util.bottomNavGap
import com.lexwilliam.risuto.util.intToCurrency
import com.lexwilliam.risuto.util.watchStatusToString

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(
    state: (ProfileContract.State),
    onEventSent: (ProfileContract.Event) -> Unit,
    navToDetail: (Int) -> Unit,
) {
    if(state.isLoading) {
        LoadingScreen()
    } else {
        ProfileContent(
            myAnimeList = state.animes,
            username = state.username,
            navToDetail = { navToDetail(it) }
        )
    }

}

@ExperimentalFoundationApi
@Composable
fun ProfileContent(
    myAnimeList: List<UserAnimeListPresentation.Data>,
    username: String,
    navToDetail: (Int) -> Unit
) {
    Column(modifier = Modifier.padding(bottom = bottomNavGap)) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.secondary,
            title = { Text("$username Anime List")},
            actions = { Icon(imageVector = Icons.Default.Notifications, contentDescription = null)}
        )
        var currentStatus by remember { mutableStateOf("All")}
        ProfileTabRow(
            status = currentStatus,
            setGroupBy = { currentStatus = it }
        )
        var filteredList: List<UserAnimeListPresentation.Data> = emptyList()
        when(currentStatus) {
            "All" -> filteredList = myAnimeList
            "Watching" -> filteredList = myAnimeList.filter { it.listStatus.status == WatchStatusPresentation.Watching }
            "Completed" -> filteredList = myAnimeList.filter { it.listStatus.status == WatchStatusPresentation.Completed }
            "Plan To Watch" -> filteredList = myAnimeList.filter { it.listStatus.status == WatchStatusPresentation.PlanToWatch }
            "On Hold" -> filteredList = myAnimeList.filter { it.listStatus.status == WatchStatusPresentation.OnHold }
            "Dropped" -> filteredList = myAnimeList.filter { it.listStatus.status == WatchStatusPresentation.Dropped }
        }
        MyAnimeGridList(items = filteredList, navToDetail = { navToDetail(it) })
    }
}

@Composable
fun ProfileTabRow(
    status: String,
    setGroupBy: (String) -> Unit
) {
    val watchStatusTabRow = listOf("All", "Watching", "Completed", "Plan To Watch", "On Hold", "Dropped")
    var currentIndex by remember { mutableStateOf(watchStatusTabRow.indexOf(status)) }
    ScrollableTabRow(
        backgroundColor = MaterialTheme.colors.background,
        selectedTabIndex = currentIndex
    ) {
        watchStatusTabRow.forEachIndexed { index, status ->
            Tab(
                selected = index == currentIndex,
                onClick = {
                    currentIndex = index
                    setGroupBy(status)
                }
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = status, style = MaterialTheme.typography.subtitle1)
                }
            }
        }
    }
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
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
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


@ExperimentalFoundationApi
@Composable
fun MyAnimeGridList(
    modifier: Modifier = Modifier,
    items: List<UserAnimeListPresentation.Data>,
    navToDetail: (Int) -> Unit
) {
    if(items.isEmpty()) {
        NoAnimeScreen()
    } else {
        LazyVerticalGrid(
            modifier = modifier.padding(start = 16.dp),
            cells = GridCells.Adaptive(minSize = 136.dp),
        ) {
            items(items = items) { item ->
                MyAnimeGrid(item = item, modifier = Modifier.padding(top = 16.dp, end = 16.dp), navToDetail = { navToDetail(it) })
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

//@Composable
//fun MyAnimeRow(
//    modifier: Modifier = Modifier,
//    item: MyAnimePresentation,
//    navToDetail: (Int) -> Unit
//) {
//    Row(
//        modifier
//            .fillMaxWidth()
//            .clickable {
//                navToDetail(item.mal_id)
//            }
//            .height(180.dp)) {
//        NetworkImage(
//            imageUrl = item.image_url,
//            modifier = Modifier
//                .size(width = 120.dp, height = 180.dp)
//                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, true)
//        )
//        Column(modifier = Modifier
//            .padding(start = 8.dp),
//            verticalArrangement = Arrangement.spacedBy(4.dp)
//        ) {
//            Text(
//                text = item.title,
//                style = MaterialTheme.typography.subtitle1,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = item.myScore.toString(),
//                style = MaterialTheme.typography.subtitle1,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = watchStatusToString(item.watchStatus),
//                style = MaterialTheme.typography.subtitle1,
//                fontWeight = FontWeight.Bold
//            )
//        }
//    }
//}
//
//@ExperimentalFoundationApi
//@Composable
//fun MyAnimeColumnList(
//    modifier: Modifier = Modifier,
//    items: List<MyAnimePresentation>,
//    navToDetail: (Int) -> Unit
//) {
//    val grouped = items.groupBy { it.watchStatus }
//    if(items.isEmpty()) {
//        LoadingScreen()
//    } else {
//        LazyColumn(
//            modifier = modifier
//        ) {
//            grouped.forEach { (watchStatus, animes) ->
//                stickyHeader {
//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(MaterialTheme.colors.background)
//                            .padding(start = 16.dp),
//                        text = watchStatusToString(watchStatus),
//                        style = MaterialTheme.typography.h5
//                    )
//                }
//                items(items = animes) { item ->
//                    MyAnimeRow(item = item, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), navToDetail = { navToDetail(it) })
//                }
//            }
//        }
//    }
//}
