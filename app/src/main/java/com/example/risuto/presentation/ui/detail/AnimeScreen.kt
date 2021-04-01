package com.example.risuto.presentation.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimePresentation
import com.example.risuto.presentation.ui.component.NetworkImage
import com.example.risuto.ui.theme.Typography

@Composable
fun AnimeScreen(
    viewModel: AnimeViewModel = viewModel(),
    onBackPressed: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    AnimeContent(
        animeDetail = viewState.animeDetail,
        onBackPressed = { onBackPressed() }
    )
}

@Composable
fun AnimeContent(
    animeDetail: AnimePresentation,
    onBackPressed: () -> Unit
) {
    Column {
        AnimeToolBar(onBackPressed = { onBackPressed() })
        AnimeDetail(animeDetail = animeDetail)
    }
}

@Composable
fun AnimeToolBar(
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        actions = {}
    )
}

@Composable
fun AnimeDetail(
    animeDetail: AnimePresentation
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        NetworkImage(modifier = Modifier.padding(top = 16.dp),
            imageUrl = animeDetail.image_url, width = 160.dp, height = 240.dp)
        Text(text = animeDetail.title, style = Typography.h6)
        Text(text = animeDetail.score.toString() + " | " + animeDetail.members, style = Typography.h5)
    }
}
