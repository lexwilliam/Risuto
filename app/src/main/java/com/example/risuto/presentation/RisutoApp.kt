package com.example.risuto.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
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

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RisutoApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                    label = { Text("Home") },
                    selected = currentRoute == RisutoHomeScreen.route,
                    onClick = {
                        navController.navigate(RisutoHomeScreen.route) {
                            popUpTo = navController.graph.startDestination
                            launchSingleTop = true
                        }
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    label = { Text("Search") },
                    selected = currentRoute == RisutoSearchScreen.route,
                    onClick = {
                        navController.navigate(RisutoSearchScreen.route) {
                            popUpTo = navController.graph.startDestination
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) {
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
                    viewModel = searchViewModel,
                    navToDetail = { mal_id ->
                        navController.navigate(
                            RisutoAnimeScreen.route.plus("/?mal_id=$mal_id")
                        )
                    }
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
}

sealed class Screens(val route: String) {
    object RisutoHomeScreen: Screens("home")
    object RisutoAnimeScreen: Screens("anime")
    object RisutoSearchScreen: Screens("search")
}