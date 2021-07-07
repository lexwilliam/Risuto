package com.example.risuto.presentation.ui.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.data.local.model.WatchStatus
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.model.MyAnimePresentation
import com.example.risuto.presentation.ui.component.*
import com.example.risuto.presentation.util.bottomNavGap
import com.example.risuto.presentation.util.intToCurrency
import com.example.risuto.presentation.util.watchStatusToString

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    navToDetail: (Int) -> Unit,
) {
    val viewState by viewModel.state.collectAsState()
    ProfileContent(
        myAnimelist = viewState.myAnimeList,
        navToDetail = { navToDetail(it) }
    )
}

@ExperimentalFoundationApi
@Composable
fun ProfileContent(
    myAnimelist: List<MyAnimePresentation>,
    navToDetail: (Int) -> Unit
) {
    Column(modifier = Modifier.padding(bottom = bottomNavGap)) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.secondary,
            title = { Text("My Anime List")},
            actions = { Icon(imageVector = Icons.Default.Notifications, contentDescription = null)}
        )
        var currentStatus by remember { mutableStateOf("All")}
        ProfileTabRow(
            status = currentStatus,
            setGroupBy = { currentStatus = it }
        )
        var filteredList: List<MyAnimePresentation> = emptyList()
        when(currentStatus) {
            "All" -> filteredList = myAnimelist
            "Watching" -> filteredList = myAnimelist.filter { it.watchStatus == WatchStatus.Watching }
            "Completed" -> filteredList = myAnimelist.filter { it.watchStatus == WatchStatus.Completed }
            "Plan To Watch" -> filteredList = myAnimelist.filter { it.watchStatus == WatchStatus.PlanToWatch }
            "On Hold" -> filteredList = myAnimelist.filter { it.watchStatus == WatchStatus.OnHold }
            "Dropped" -> filteredList = myAnimelist.filter { it.watchStatus == WatchStatus.Dropped }
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
    item: MyAnimePresentation,
    navToDetail: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                navToDetail(item.mal_id)
            }
    ) {
        NetworkImage(
            imageUrl = item.image_url,
            modifier = Modifier
                .size(width = 180.dp, height = 240.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, clip = true)
        )
        Text(text = item.title,
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
            Text(text = watchStatusToString(item.watchStatus), style = MaterialTheme.typography.caption)
            Text(text = intToCurrency(item.myScore), style = MaterialTheme.typography.caption)
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun MyAnimeGridList(
    modifier: Modifier = Modifier,
    items: List<MyAnimePresentation>,
    navToDetail: (Int) -> Unit
) {
    if(items.isEmpty()) {
        LoadingScreen()
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
