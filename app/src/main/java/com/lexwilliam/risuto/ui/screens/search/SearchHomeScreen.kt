package com.lexwilliam.risuto.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.lexwilliam.risuto.ui.component.Header
import com.lexwilliam.risuto.ui.theme.RisutoTheme
import com.lexwilliam.risuto.util.genreList
import com.lexwilliam.risuto.util.getGenre

@Composable
fun SearchHomeScreen(
    navToSearch: () -> Unit,
    navToSearchWithGenre: (Int) -> Unit
) {
    SearchHomeContent(
        navToSearch = { navToSearch() },
        navToSearchWithGenre = { navToSearchWithGenre(it) }
    )
}

@Composable
fun SearchHomeContent(
    navToSearch: () -> Unit,
    navToSearchWithGenre: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { Header(title = "Search", modifier = Modifier.padding(top = 24.dp)) }
        item { SearchHomeBar(navToSearch = { navToSearch() }) }
        item { Text(text = "Genre", style = MaterialTheme.typography.h6) }
        item {
            FlowRow(mainAxisSize = SizeMode.Expand, mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween) {
                genreList.forEach { genre ->
                    GenreGrid(modifier = Modifier.padding(bottom = 16.dp), genre = genre, navToSearchWithGenre = { navToSearchWithGenre(it) })
                }
            }
        }
        item { Spacer(Modifier.padding(48.dp)) }
    }
}

@Composable
fun SearchHomeBar(
    navToSearch: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.surface)
            .clickable {
                navToSearch()
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.padding(4.dp))
            Icon(Icons.Default.Search, contentDescription = null)
            Text(text = "Search Animes", style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
fun GenreGrid(
    modifier: Modifier = Modifier,
    genre: String,
    navToSearchWithGenre: (Int) -> Unit
) {
    Column(modifier = modifier
        .size(width = 172.dp, height = 64.dp)
        .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, true)
        .background(color = MaterialTheme.colors.primaryVariant)
        .clickable {
            navToSearchWithGenre(getGenre(genre))
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = genre, style = MaterialTheme.typography.subtitle2, color = Color.White)
    }
}

@Preview
@Composable
fun SearchHomePreview() {
    RisutoTheme {
        Box(
            Modifier.background(MaterialTheme.colors.background)
        ) {
            SearchHomeContent(
                navToSearch = {},
                navToSearchWithGenre = {}
            )
        }
    }

}