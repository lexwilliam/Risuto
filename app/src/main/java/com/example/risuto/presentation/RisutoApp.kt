package com.example.risuto.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.risuto.presentation.Screens.*
import com.example.risuto.presentation.ui.detail.AnimeScreen
import com.example.risuto.presentation.ui.detail.AnimeViewModel
import com.example.risuto.presentation.ui.detail.AnimeViewState
import com.example.risuto.presentation.ui.home.HomeScreen
import com.example.risuto.presentation.ui.home.HomeViewModel
import com.example.risuto.presentation.ui.search.SearchScreen
import com.example.risuto.presentation.ui.search.SearchViewModel

@Composable
fun RisutoApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable(RisutoHomeScreen.route) {
            val homeViewModel = hiltNavGraphViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = homeViewModel,
                navToDetail = { mal_id ->
                    navController.navigate(
                        RisutoAnimeScreen.route.plus("/?mal_id=$mal_id")
                    )
                },
                navToSearch = {
                    navController.navigate(RisutoSearchScreen.route)
                }
            )
        }
        composable(RisutoSearchScreen.route) {
            val searchViewModel = hiltNavGraphViewModel<SearchViewModel>()
            SearchScreen(
                viewModel = searchViewModel
            )
        }
        composable(
            route = RisutoAnimeScreen.route.plus("/?mal_id={mal_id}"),
            arguments = listOf(
                navArgument("mal_id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            val animeViewModel = hiltNavGraphViewModel<AnimeViewModel>()
            AnimeScreen(
                viewModel = animeViewModel,
                onBackPressed = { navController.navigateUp() }
            )
        }
    }
}

sealed class Screens(val route: String) {
    object RisutoHomeScreen: Screens("home")
    object RisutoAnimeScreen: Screens("anime")
    object RisutoSearchScreen: Screens("search")
}