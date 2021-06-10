package com.example.risuto.presentation.ui.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.MyAnimePresentation
import com.example.risuto.presentation.ui.component.Header
import com.example.risuto.presentation.ui.component.LoadingScreen
import com.example.risuto.presentation.ui.component.NetworkImage
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
    Column(modifier = Modifier.padding(bottom = 64.dp)) {
        Header(title = "Profile", modifier = Modifier.padding(16.dp))
        MyAnimeColumnList(items = myAnimelist, navToDetail = { navToDetail(it) })
    }
}

@Composable
fun MyAnimeRow(
    modifier: Modifier = Modifier,
    item: MyAnimePresentation,
    navToDetail: (Int) -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .clickable {
                navToDetail(item.mal_id)
            }
            .height(180.dp)) {
        NetworkImage(
            imageUrl = item.image_url,
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, true)
        )
        Column(modifier = Modifier
            .padding(start = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.myScore.toString(),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = watchStatusToString(item.watchStatus),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MyAnimeColumnList(
    modifier: Modifier = Modifier,
    items: List<MyAnimePresentation>,
    navToDetail: (Int) -> Unit
) {
    val grouped = items.groupBy { it.watchStatus }
    if(items.isEmpty()) {
        LoadingScreen()
    } else {
        LazyColumn(
            modifier = modifier
        ) {
            grouped.forEach { (watchStatus, animes) ->
                stickyHeader {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.background)
                            .padding(start = 16.dp),
                        text = watchStatusToString(watchStatus),
                        style = MaterialTheme.typography.h5
                    )
                }
                items(items = animes) { item ->
                    MyAnimeRow(item = item, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), navToDetail = { navToDetail(it) })
                }
            }
        }
    }
}
