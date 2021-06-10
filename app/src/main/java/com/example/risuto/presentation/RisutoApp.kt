package com.example.risuto.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.view.Window
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
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
import com.example.risuto.presentation.ui.profile.ProfileScreen
import com.example.risuto.presentation.ui.profile.ProfileViewModel
import com.example.risuto.presentation.ui.search.SearchHomeScreen
import com.example.risuto.presentation.ui.search.SearchScreen
import com.example.risuto.presentation.ui.search.SearchViewModel
import com.example.risuto.presentation.ui.season.SeasonScreen
import com.example.risuto.presentation.ui.season.SeasonViewModel

@ExperimentalMaterialApi
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

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RisutoAppContent() {
    val navController = rememberNavController()

    data class BottomNavItem(
        val icon: ImageVector,
        val route: String,
        val description: String
    )

    val bottomNavIcons = listOf(
        BottomNavItem(Icons.Filled.Home, RisutoHomeScreen.route, "Home"),
        BottomNavItem(Icons.Filled.Search, RisutoSearchHomeScreen.route, "Search"),
        BottomNavItem(Icons.Default.DateRange, RisutoSeasonScreen.route, "Season"),
        BottomNavItem(Icons.Filled.Person, RisutoProfileScreen.route, "Profile")
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.secondary,
                elevation = 16.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination

                bottomNavIcons.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        selected = currentRoute?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        },
                        label = { Text(screen.description) }
                    )
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable(RisutoHomeScreen.route) {
                val homeViewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(
                    viewModel = homeViewModel,
                    navToDetail = { mal_id ->
                        navController.navigate(
                            RisutoAnimeScreen.route.plus("/?mal_id=$mal_id")
                        )
                    }
                )
            }
            composable(RisutoSearchHomeScreen.route) {
                SearchHomeScreen(
                    navToSearch = {
                        navController.navigate(
                            RisutoSearchScreen.route
                        )
                    },
                    navToGenre = { genre_id ->
                        navController.navigate(
                            RisutoGenreScreen.route.plus("/?genre_id=$genre_id")
                        )
                    }
                )
            }
            composable(RisutoSearchScreen.route) {
                val searchViewModel = hiltViewModel<SearchViewModel>()
                SearchScreen(
                    viewModel = searchViewModel,
                    navToDetail = { mal_id ->
                        navController.navigate(
                            RisutoAnimeScreen.route.plus("/?mal_id=$mal_id")
                        )
                    },
                    onBackPressed = {
                        navController.navigateUp()
                    }
                )
            }
            composable(RisutoSeasonScreen.route) {
                val seasonViewModel = hiltViewModel<SeasonViewModel>()
                SeasonScreen(
                    viewModel = seasonViewModel,
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
                val animeViewModel = hiltViewModel<AnimeViewModel>()
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
                val genreViewModel = hiltViewModel<GenreViewModel>()
                GenreScreen(
                    viewModel = genreViewModel,
                    onBackPressed = { navController.navigateUp() },
                    navToDetail = { mal_id ->
                        navController.navigate(
                            RisutoAnimeScreen.route.plus("/?mal_id=$mal_id")
                        )
                    }
                )
            }
            composable(RisutoProfileScreen.route) {
                val profileViewModel = hiltViewModel<ProfileViewModel>()
                ProfileScreen(
                    viewModel = profileViewModel,
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
    object RisutoSearchHomeScreen: Screens("searchHome")
    object RisutoSearchScreen: Screens("search")
    object RisutoSeasonScreen: Screens("season")
    object RisutoAnimeScreen: Screens("anime")
    object RisutoGenreScreen: Screens("genre")
    object RisutoProfileScreen: Screens("profile")
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