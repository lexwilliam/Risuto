package com.example.risuto.presentation

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.risuto.R
import com.example.risuto.presentation.Screens.*
import com.example.risuto.presentation.ui.detail.AnimeScreen
import com.example.risuto.presentation.ui.detail.AnimeViewModel
import com.example.risuto.presentation.ui.genre.GenreScreen
import com.example.risuto.presentation.ui.genre.GenreViewModel
import com.example.risuto.presentation.ui.home.HomeScreen
import com.example.risuto.presentation.ui.home.HomeViewModel
import com.example.risuto.presentation.ui.search.SearchScreen
import com.example.risuto.presentation.ui.search.SearchViewModel

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RisutoApp() {
    val context = LocalContext.current
    var isOnline by remember { mutableStateOf(checkIfOnline(context)) }
    if (isOnline) {
        RisutoAppContent()
    } else {
        OfflineDialog { isOnline = checkIfOnline(context) }
    }
}

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RisutoAppContent() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.secondary,
                elevation = 0.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
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
                    },
                    navToGenre = { genre_id ->
                        navController.navigate(
                            RisutoGenreScreen.route.plus("/?genre_id=$genre_id")
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
                    onBackPressed = { navController.navigateUp() },
                    navToGenre = { genre_id ->
                        navController.navigate(
                            RisutoGenreScreen.route.plus("/?genre_id=$genre_id")
                        )
                    }
                )
            }
            composable(
                route = RisutoGenreScreen.route.plus("/?genre_id={genre_id}"),
                arguments = listOf(
                    navArgument("genre_id") {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) {
                val genreViewModel = hiltNavGraphViewModel<GenreViewModel>()
                GenreScreen(
                    viewModel = genreViewModel,
                    onBackPressed = { navController.navigateUp()},
                    navToDetail = { mal_id ->
                        navController.navigate(
                            RisutoAnimeScreen.route.plus("/?mal_id=$mal_id")
                        )
                    }
                )
            }
        }
    }
}

sealed class Screens(val route: String) {
    object RisutoHomeScreen: Screens("home")
    object RisutoSearchScreen: Screens("search")
    object RisutoAnimeScreen: Screens("anime")
    object RisutoGenreScreen: Screens("genre")
}

@Suppress("DEPRECATION")
private fun checkIfOnline(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

@Composable
fun OfflineDialog(onRetry: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Connection Error") },
        text = { Text(text = "Unable to fetch anime list. Please check your connection") },
        confirmButton = {
            TextButton(onClick = onRetry) {
                Text(stringResource(R.string.retry_label))
            }
        }
    )
}