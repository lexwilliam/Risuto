package com.lexwilliam.risuto

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.BottomNavigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lexwilliam.risuto.Screens.*
import com.lexwilliam.risuto.ui.screens.detail.AnimeScreen
import com.lexwilliam.risuto.ui.screens.detail.AnimeViewModel
import com.lexwilliam.risuto.ui.screens.home.HomeScreen
import com.lexwilliam.risuto.ui.screens.home.HomeViewModel
import com.lexwilliam.risuto.ui.screens.login.LoginScreen
import com.lexwilliam.risuto.ui.screens.login.LoginViewModel
import com.lexwilliam.risuto.ui.screens.profile.MyAnimeScreen
import com.lexwilliam.risuto.ui.screens.profile.MyAnimeViewModel
import com.lexwilliam.risuto.ui.screens.search.SearchHomeScreen
import com.lexwilliam.risuto.ui.screens.search.SearchScreen
import com.lexwilliam.risuto.ui.screens.search.SearchViewModel
import com.lexwilliam.risuto.ui.screens.season.SeasonScreen
import com.lexwilliam.risuto.ui.screens.season.SeasonViewModel
import com.lexwilliam.risuto.ui.screens.splash.SplashScreen
import com.lexwilliam.risuto.ui.screens.splash.SplashViewModel

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RisutoApp(
    authCode: String?
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
    }
    val context = LocalContext.current
    var isOnline by remember { mutableStateOf(checkIfOnline(context)) }
    if (isOnline) {
        RisutoAppContent(
            authCode = authCode
        )
    } else {
        OfflineDialog { isOnline = checkIfOnline(context) }
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RisutoAppContent(
    authCode: String?
) {
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
        BottomNavItem(Icons.Filled.Person, RisutoMyAnimeScreen.route, "Profile")
    )

    val bottomNavException = listOf(
        RisutoHomeScreen.route,
        RisutoSeasonScreen.route,
        RisutoSearchHomeScreen.route,
        RisutoMyAnimeScreen.route
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if(bottomNavException.contains(currentRoute?.route)) {
                BottomNavigation(
                    contentPadding = rememberInsetsPaddingValues(
                        insets = LocalWindowInsets.current.navigationBars,
                    ),
                    backgroundColor = MaterialTheme.colors.background,
                    contentColor = MaterialTheme.colors.secondary,
                    elevation = 16.dp
                ) {
                    bottomNavIcons.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(text = screen.description) },
                            selected = currentRoute?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
                Spacer(Modifier.navigationBarsHeight().fillMaxWidth())
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "splash") {

            composable(RisutoSplashScreen.route) {
                val splashViewModel = hiltViewModel<SplashViewModel>()
                SplashScreen(
                    state = splashViewModel.viewState.value,
                    onEventSent = { event -> splashViewModel.setEvent(event) },
                    navToLogin = {
                        navController.navigate(RisutoLoginScreen.route)
                    },
                    navToHome = {
                        navController.navigate(RisutoHomeScreen.route)
                    }
                )
            }

            composable(RisutoHomeScreen.route) {
                val homeViewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(
                    state = homeViewModel.viewState.value,
                    onEventSent = { event -> homeViewModel.setEvent(event)},
                    navToDetail = { mal_id ->
                        navController.navigate(
                            RisutoAnimeScreen.route.plus("/?mal_id=$mal_id")
                        )
                    }
                )
            }

            composable(RisutoLoginScreen.route) {
                val loginViewModel = hiltViewModel<LoginViewModel>()
                LoginScreen(
                    authCode = authCode,
                    state = loginViewModel.viewState.value,
                    onEventSent = { event -> loginViewModel.setEvent(event) },
                    navToHome = {
                        navController.navigate(RisutoHomeScreen.route)
                    }
                )
            }

            composable(RisutoSearchHomeScreen.route) {
                SearchHomeScreen(
                    navToSearch = {
                        navController.navigate(
                            RisutoSearchScreen.route.plus("/?genre=")
                        )
                    },
                    navToSearchWithGenre = { genre ->
                        navController.navigate(
                            RisutoSearchScreen.route.plus("/?genre=$genre")
                        )
                    }
                )
            }

            composable(
                route = RisutoSearchScreen.route.plus("/?genre={genre}"),
                arguments = listOf(
                    navArgument("genre") {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            )  {
                val searchViewModel = hiltViewModel<SearchViewModel>()
                SearchScreen(
                    state = searchViewModel.viewState.value,
                    onEventSent = { event -> searchViewModel.setEvent(event) },
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
                    state = seasonViewModel.viewState.value,
                    onEventSent = { event -> seasonViewModel.setEvent(event) },
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
                    state = animeViewModel.viewState.value,
                    onEventSent = { event -> animeViewModel.setEvent(event) },
                    onBackPressed = { navController.navigateUp() },
                    navToSearchWithGenre = { genre ->
                        navController.navigate(
                            RisutoSearchScreen.route.plus("/?genre=$genre")
                        )
                    },
                    navToDetail = { id ->
                        navController.navigate(
                            RisutoAnimeScreen.route.plus("/?mal_id=$id")
                        )
                    }
                )
            }
            composable(RisutoProfileScreen.route) {

            }
            composable(RisutoMyAnimeScreen.route) {
                val profileViewModel = hiltViewModel<MyAnimeViewModel>()
                MyAnimeScreen(
                    state = profileViewModel.viewState.value,
                    onEventSent = { event -> profileViewModel.setEvent(event)},
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
    object RisutoSplashScreen: Screens("splash")
    object RisutoLoginScreen: Screens("login")
    object RisutoHomeScreen: Screens("home")
    object RisutoSearchHomeScreen: Screens("searchHome")
    object RisutoSearchScreen: Screens("search")
    object RisutoSeasonScreen: Screens("season")
    object RisutoAnimeScreen: Screens("anime")
    object RisutoMyAnimeScreen: Screens("myAnime")
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
                Text("Retry")
            }
        }
    )
}