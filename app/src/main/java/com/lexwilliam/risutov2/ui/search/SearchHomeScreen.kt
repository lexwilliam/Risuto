package com.lexwilliam.risutov2.ui.search

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import com.lexwilliam.risutov2.ui.component.Header
import com.lexwilliam.risutov2.util.genreList
import com.lexwilliam.risutov2.util.getGenre

@Composable
fun SearchHomeScreen(
    navToSearch: () -> Unit,
    navToGenre: (Int) -> Unit
) {
    SearchHomeContent(
        navToSearch = { navToSearch() },
        navToGenre = { navToGenre(it) }
    )
}

@Composable
fun SearchHomeContent(
    navToSearch: () -> Unit,
    navToGenre: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 64.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Header(title = "Search", modifier = Modifier.padding(top = 24.dp))
        SearchHomeBar(navToSearch = { navToSearch() })
        Text(text = "Genre", style = MaterialTheme.typography.h6)
        GenreGridList(navToGenre = {
            navToGenre(it)
        })
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
fun GenreGridList(
    navToGenre: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var count = 0
        val size = _root_ide_package_.com.lexwilliam.risutov2.util.genreList.size - _root_ide_package_.com.lexwilliam.risutov2.util.genreList.size%2
        while(count < size) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                for (i in 0..1) {
                    GenreGrid(
                        genre = _root_ide_package_.com.lexwilliam.risutov2.util.genreList[count],
                        navToGenre = {
                            navToGenre(it)
                        }
                    )
                    count++
                }
            }
        }
        if (_root_ide_package_.com.lexwilliam.risutov2.util.genreList.size - count != 0) {
            Row {
                while (_root_ide_package_.com.lexwilliam.risutov2.util.genreList.size - count != 0) {
                    GenreGrid(
                        genre = _root_ide_package_.com.lexwilliam.risutov2.util.genreList[count],
                        navToGenre = {
                            navToGenre(it)
                        }
                    )
                    count++
                }
            }
        }
    }
}

@Composable
fun GenreGrid(
    modifier: Modifier = Modifier,
    genre: String,
    navToGenre: (Int) -> Unit
) {
    Column(modifier = modifier
        .size(width = 172.dp, height = 64.dp)
        .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium, true)
        .background(color = MaterialTheme.colors.primaryVariant)
        .clickable {
            navToGenre(_root_ide_package_.com.lexwilliam.risutov2.util.getGenre(genre))
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = genre, style = MaterialTheme.typography.subtitle2, color = Color.White)
    }
}
