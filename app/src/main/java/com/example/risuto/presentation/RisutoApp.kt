package com.example.risuto.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.risuto.presentation.ui.detail.AnimeScreen
import com.example.risuto.presentation.ui.home.HomeScreen
import com.example.risuto.presentation.ui.home.HomeViewModel
import com.example.risuto.presentation.ui.search.SearchScreen

@Composable
fun RisutoApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            val homeViewModel = hiltNavGraphViewModel<HomeViewModel>()
            HomeScreen(homeViewModel)
        }
        composable("anime") { AnimeScreen() }
        composable("search") { SearchScreen() }
    }
}