package com.example.risuto.presentation.ui.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimePresentation
import com.example.risuto.presentation.model.MyAnimePresentation
import com.example.risuto.presentation.ui.component.GridList
import com.example.risuto.presentation.ui.component.MyAnimeGridList
import com.example.risuto.presentation.ui.component.MyAnimeMediumGrid

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
    MyAnimeGridList(items = myAnimelist, navToDetail = { navToDetail(it) })
}